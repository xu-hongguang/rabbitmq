package com.xhg.rabbitmq.amqp;

import com.xhg.rabbitmq.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class MQReceiver {
    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @RabbitListener(queues = MQConfig.DIRECT_QUEUE)
    public void receiveDirect(String msg) {
        log.info("Direct Receive : " + msg);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE_1)
    public void receiveTopic1(String msg) {
        log.info("Topic1 Receive : " + msg);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE_2)
    public void receiveTopic2(String msg) {
        log.info("Topic2 Receive : " + msg);
    }

    @RabbitListener(queues = MQConfig.FANOUT_QUEUE_1)
    public void receiveFanout1(String msg) {
        log.info("Fanout1 Receive : " + msg);
    }

    @RabbitListener(queues = MQConfig.FANOUT_QUEUE_2)
    public void receiveFanout2(String msg) {
        log.info("Fanout2 Receive : " + msg);
    }

    @RabbitListener(queues = MQConfig.HEADERS_QUEUE)
    public void receiveHeaders(byte[] msg) {
        log.info("Headers Receive : " + new String(msg));
    }
}
