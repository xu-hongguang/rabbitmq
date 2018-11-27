package com.xhg.rabbitmq.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Rabbit配置
 * @author kingapex
 * 2017年8月2日上午11:52:50
 * @version 1.0
 * @since 6.4
 */
@Configuration
public class RabbitConfiguration {
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory =new CachingConnectionFactory("localhost",5672);
	    connectionFactory.setUsername("eddy");
	    connectionFactory.setPassword("123456");
	    return connectionFactory;
	}
 
	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory()) ;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	
}
