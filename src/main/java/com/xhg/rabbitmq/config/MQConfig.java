package com.xhg.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MQConfig {

    // 四种模式：Direct，Topic，Fanout，Header

    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DIRECT_KEY = "direct_key";

    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";
    public static final String TOPIC_QUEUE_3 = "topic_queue_3";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String TOPIC_EXCHANGE2 = "topic_exchange2";
    public static final String ROUTINE_KEY_1 = "topic.key1";
    // 可以使用通配符
    public static final String ROUTINE_KEY_2 = "topic.*";
    public static final String ROUTINE_KEY_3= "topic.key3";

    public static final String FANOUT_QUEUE_1 = "fanout_queue_1";
    public static final String FANOUT_QUEUE_2 = "fanout_queue_2";
    public static final String FANOUT_QUEUE_3 = "fanout_queue_3";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
//    public static final String FANOUT_KEY = "fanout_key";

    public static final String HEADERS_QUEUE = "headers_queue";
    public static final String HEADERS_EXCHANGE = "headers_exchange";

    // Direct模式
    @Bean
    public Queue directQueue() {
        // 创建一个队列
        return new Queue(DIRECT_QUEUE, true);
    }
    @Bean
    public DirectExchange directExchange(){
        // 创建一个 direct 类型的交换器
        return new DirectExchange(DIRECT_EXCHANGE);
    }
    @Bean
    public Binding directBinding(){
        // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECT_KEY);
    }

    // Topic模式：根据RoutineKey去绑定接收的消息
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_1, true);
    }
    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_2, true);
    }
    @Bean
    public Queue topicQueue3() {
        return new Queue(TOPIC_QUEUE_3, true);
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public TopicExchange topicExchange2() {
        return new TopicExchange(TOPIC_EXCHANGE2);
    }
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTINE_KEY_1);
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTINE_KEY_2);
    }
    @Bean
    public Binding topicBinding3() {
        return BindingBuilder.bind(topicQueue3()).to(topicExchange2()).with(ROUTINE_KEY_3);
    }

    // Fanout模式：广播
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_1, true);
    }
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_2, true);
    }
    @Bean
    public Queue fanoutQueue3() {
        return new Queue(FANOUT_QUEUE_3, true);
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding3() {
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());
    }

    // Headers模式：只有检验头部的KV是一致的才会接收到消息
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }
    @Bean
    public Queue headerQueue() {
        return new Queue(HEADERS_QUEUE, true);
    }
    @Bean
    public Binding headerBinding() {
        Map<String, Object> map = new HashMap<>();
        map.put("h1", "v1");
        map.put("h2", "v2");
        return BindingBuilder.bind(headerQueue()).to(headersExchange()).whereAll(map).match();
    }

}
