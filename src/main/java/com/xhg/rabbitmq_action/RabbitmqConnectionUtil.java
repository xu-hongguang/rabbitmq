package com.xhg.rabbitmq_action;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 16033
 */
public class RabbitmqConnectionUtil {
    private static RabbitmqConnectionUtil util = new RabbitmqConnectionUtil();
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;

    private RabbitmqConnectionUtil(){}

    public static RabbitmqConnectionUtil getInstance(){
        return util;
    }

    public Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("xhg");
        factory.setPassword("1234");
        //  创建连接
        return factory.newConnection();
    }

    public void close(){

    }
}
