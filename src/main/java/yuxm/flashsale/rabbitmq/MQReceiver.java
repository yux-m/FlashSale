package yuxm.flashsale.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuxm.flashsale.entity.FlashsaleMessage;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.service.IOrderService;
import yuxm.flashsale.service.IProductService;
import yuxm.flashsale.utils.JsonUtil;
import yuxm.flashsale.vo.ProductVO;

import java.io.IOException;

/**
 * Message consumer.
 */
@Service
@Slf4j
public class MQReceiver {
    @Autowired
    private IProductService productService;
    //    @Autowired
//    private RedisTemplate redisTemplate;
    @Autowired
    private IOrderService orderService;


    @RabbitListener(queues = "flashsaleQueue")
    public void receive(String msg) throws IOException {
        log.info("Receive message: " + msg);
        FlashsaleMessage flashsaleMessage = JsonUtil.jsonStr2Object(msg, FlashsaleMessage.class);
        Long productId = flashsaleMessage.getProductId();
        User user = flashsaleMessage.getUser();
        ProductVO productVO = productService.findProductVoByProductId(productId);
        if (productVO.getStockCount() < 1) {
            return;
        }
        //create order
        orderService.createOrder(user, productVO);
    }

//    /**
//     * For mq testing.
//     * @param msg
//     */
//    public void receive(Object msg) {
//        log.info("Receive message: " + msg);
//    }
}
