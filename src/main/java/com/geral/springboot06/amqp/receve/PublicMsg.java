package com.geral.springboot06.amqp.receve;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.geral.springboot06.amqp.domin.Book;

/**
 * 广播信息
 * @author Panhui
 *
 */
@Component
public class PublicMsg {
	@RabbitHandler
	@RabbitListener(queues="panpan.news")
	public void signleInfo1(Book book) 
	{
		System.out.println("panpan.news收到消息="+book.toString());
	}
	@RabbitHandler
	@RabbitListener(queues="panpan.emps")
	public void signleInfo2(Book book) 
	{
		System.out.println("panpan.emps收到消息="+book.toString());
	}
	@RabbitHandler
	@RabbitListener(queues="panpan")
	public void signleInfo3(Book book) 
	{
		System.out.println("panpan收到消息="+book.toString());
	}
	@RabbitHandler
	@RabbitListener(queues="panwork.news")
	public void signleInfo4(Book book) 
	{
		System.out.println("panwork.news收到消息="+book.toString());
	}
}
