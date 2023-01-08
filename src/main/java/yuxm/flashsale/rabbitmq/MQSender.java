package yuxm.flashsale.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Message producer.
 */
@Service
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Send flash sale message.
     *
     * @param message
     */
    public void sendFlashsaleMessage(String message) {
        log.info("Send message: " + message);
        rabbitTemplate.convertAndSend("flashsaleExchange", "flashsale.message", message);
    }

//    /**
//     * For mq testing.
//     * @param msg
//     */
//    public void send(Object msg) {
//        log.info("Send message：" + msg);
//        rabbitTemplate.convertAndSend("flashsaleQueue", msg);
//    }
}