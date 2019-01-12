package com.geral.springboot06.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置消息传递格式
 * @author Panhui
 *
 */
@Configuration
public class MyAMQPConfig {
	
	/**
	 * 转换为json格式
	 * @return
	 */
	@Bean
	public MessageConverter messageConverter() 
	{
		return new Jackson2JsonMessageConverter();
	}
}
