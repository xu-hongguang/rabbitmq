package com.xhg.rabbitmq.amqp;

import com.xhg.rabbitmq.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送者
 */
@Component
public class MQSender {
    private static Logger logger = LoggerFactory.getLogger(MQSender.class);

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public MQSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendDirect() {
        String msg = "DirectMsg";
        logger.info("send msg : " + msg);
        amqpTemplate.convertAndSend(MQConfig.DIRECT_QUEUE, msg);
    }

    public void sendTopic() {
        String msg = "TopicMsg";
        logger.info("send msg : " + msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg + "_1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg + "_2");
    }

    public void sendFanout() {
        String msg = "FanoutMsg";
        logger.info("send msg : " + msg);
        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
    }

    public void sendHeaders() {
        String msg = "HeadersMsg";
        logger.info("send msg : " + msg);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("h1", "v1");
        messageProperties.setHeader("h2", "v2");
        Message message = new Message(msg.getBytes(), messageProperties);
        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", message);
    }
}
