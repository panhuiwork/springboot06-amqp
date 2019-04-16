package com.geral.springboot06.amqp.receve;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.geral.springboot06.amqp.domin.Book;

@Component
public class SingleMsg {

	@RabbitHandler
	@RabbitListener(queues="panpan.news")
	public void signleInfo1(Book book) 
	{
		System.out.println("1收到消息="+book.toString());
	}
	@RabbitHandler
	@RabbitListener(queues="panpan.news")
	public void signleInfo2(Book book) 
	{
		System.out.println("2收到消息="+book.toString());
	}
	
	@RabbitHandler
	@RabbitListener(queues="atguigu")
	public void signleInfo3(Book book) 
	{
		System.out.println("3收到消息="+book.toString());
	}
	
	
}
