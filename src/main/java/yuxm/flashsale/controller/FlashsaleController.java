package yuxm.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yuxm.flashsale.entity.Order;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.service.IOrderService;
import yuxm.flashsale.service.IProductService;
import yuxm.flashsale.vo.ProductVO;
import yuxm.flashsale.vo.RespBeanEnum;

import java.util.Date;

@Controller
@RequestMapping("/flashsale")
public class FlashsaleController {

    @Autowired
    IProductService productService;
    @Autowired
    IOrderService orderService;

    @RequestMapping("/checkout")
    public String checkout(Model model, User user, Long productId) {
        if (user == null) return "login";
        model.addAttribute("user", user);
        ProductVO productVO = productService.findProductVoByProductId(productId);
        //stock check
        if (productVO.getStockCount() < 1 && productVO.getStockCount() != -1) {
            //error message
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "purchaseFail";
        }
        //create order
        Order order = orderService.createOrder(user, productVO);
        model.addAttribute("order", order);
        model.addAttribute("product", productVO);
        return "checkout";
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
}
