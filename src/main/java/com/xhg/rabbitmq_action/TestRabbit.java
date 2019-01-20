package com.xhg.rabbitmq_action;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestRabbit {
    public static void main(String[] args) {
        try {
            Connection connection = RabbitmqConnectionUtil.getInstance().getConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("topic_exchange","topic",true);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName,"topic_exchange","routing_key_topic");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
