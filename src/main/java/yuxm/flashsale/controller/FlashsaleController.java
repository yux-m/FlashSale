package yuxm.flashsale.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yuxm.flashsale.entity.FlashsaleMessage;
import yuxm.flashsale.entity.Order;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.exception.GlobalException;
import yuxm.flashsale.rabbitmq.MQSender;
import yuxm.flashsale.service.IOrderService;
import yuxm.flashsale.service.IProductService;
import yuxm.flashsale.utils.JsonUtil;
import yuxm.flashsale.vo.ProductVO;
import yuxm.flashsale.vo.RespBean;
import yuxm.flashsale.vo.RespBeanEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/flashsale")
public class FlashsaleController implements InitializingBean {

    /**
     * A map to track whether a product's stock is empty, to reduce communication with Redis.
     * Key: (Long) productId
     * Value: (Boolean) stock is not empty
     */
    private final Map<Long, Boolean> emptyStockMap = new HashMap<>();
    @Autowired
    IProductService productService;
    @Autowired
    IOrderService orderService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MQSender mqSender;

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @ResponseBody
    public RespBean purchase(Model model, User user, Long productId) {
        if (user == null) return RespBean.error(RespBeanEnum.SESSION_ERROR);
        model.addAttribute("user", user);
        model.addAttribute("productId", productId);

        //stock status check, to reduce redis communication
        if (emptyStockMap.get(productId)) return RespBean.error(RespBeanEnum.EMPTY_STOCK);

        //attempt to decrease stock in Redis
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Long stock = valueOperations.decrement("flashsaleProduct" + productId);
        //stock check: if original stock == 0, stock = -1 < 0 after decrement
        if (stock < 0) {
            emptyStockMap.put(productId, true); //set empty_stock status to true
            //if stock is empty, we don't want to decrement it then, so we add it back to avoid -1
            valueOperations.increment("flashsaleProduct" + productId);
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }

        FlashsaleMessage flashsaleMessage = new FlashsaleMessage(user, productId);
        //add in queue and wait for receiver to process
        mqSender.sendFlashsaleMessage(JsonUtil.object2JsonStr(flashsaleMessage));

//        ProductVO productVO = productService.findProductVoByProductId(productId);
//        //stock check
//        if (productVO.getStockCount() < 1 && productVO.getStockCount() != -1) {
//            //error message
//            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
//            return "purchaseFail";
//        }
//        //create order
//        Order order = orderService.createOrder(user, productVO);
//        model.addAttribute("order", order);
//        model.addAttribute("product", productVO);

        return RespBean.success(0);

    }

    @RequestMapping("/getResult")
    @ResponseBody
    public RespBean getResult(Model model, User user, Long productId) {
        if (user == null) throw new GlobalException(RespBeanEnum.SESSION_ERROR);

        //get order from redis
        Order order = (Order) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + productId);
        if (order == null) {
            //check current stock
            //if empty, purchase failed
            if (emptyStockMap.get(productId)) return RespBean.error(RespBeanEnum.EMPTY_STOCK);
            //stock not empty but order == null, meaning purchase attempt still in queue
            return RespBean.success(0);
        }

        ProductVO productVO = productService.findProductVoByProductId(productId);
        model.addAttribute("order", order);
        model.addAttribute("product", productVO);

        return RespBean.success(1);
    }

    @RequestMapping("/orderDetail/{orderId}")
    public String orderDetail(Model model, User user, @PathVariable Long orderId) {
        //fetch order
        Order order = orderService.findOrderByOrderId(orderId);
        order.setPaymentDate(new Date());
        orderService.updateById(order);
        model.addAttribute("order", order);
        return "orderDetail";
    }

    /**
     * Initialization. Save stock count into Redis during initialization.
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() {
        List<ProductVO> list = productService.findProductVO();
        if (list.isEmpty()) {
            return;
        }
        for (ProductVO productVO : list) {
            redisTemplate.opsForValue().set("flashsaleProduct" + productVO.getId(), productVO.getStockCount());
            //initialize empty_stock status
            if (productVO.getStockCount() > 0) {
                emptyStockMap.put(productVO.getId(), false);
            } else emptyStockMap.put(productVO.getId(), true);
        }
    }

    @RequestMapping("/checkout")
    public String checkout(Model model, User user) {
        int i = 0;
        return "checkout";
    }

    @RequestMapping("/purchaseFail")
    public String purchaseFail() {
        return "purchaseFail";
    }
}
