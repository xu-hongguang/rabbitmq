package com.xhg.rabbitmq_action;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 服务发送方
 * @author 16033
 */
public class RabbitProducer {
    private static final String EXCHANGE_NAME = "exchange_demo1";
    private static final String ROUTING_KEY = "routingkey_demo";
    private static final String QUEUE_NAME = "queue1_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("xhg");
        factory.setPassword("1234");
        //  创建连接
        Connection connection = factory.newConnection();
        //  创建信道
        Channel channel = connection.createChannel();
        //  创建一个type=“direct”、持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"topic",true,false,null);
        //  创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //  将交换机与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //  发送一条持久化消息
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        //  关闭资源
        channel.close();
        connection.close();

    }
}
