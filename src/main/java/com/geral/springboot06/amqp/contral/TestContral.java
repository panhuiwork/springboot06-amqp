package com.geral.springboot06.amqp.contral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geral.springboot06.amqp.domin.Book;

@RestController
public class TestContral {

	@Autowired
	RabbitTemplate rabbitTemplate;

	/**
	 * 发送rabbit信息 (单播)
	 * 
	 * @return
	 */
	@GetMapping("/sendmsg")
	public String sendmsg() {
		// 发送一个消息
//		//Message需要自己构造一个，定义消息体内容和消息头
////		rabbitTemplate.send(exchange, routingKey, message);
//		//只需要传入要发送的对象，自动序列化发送给rabbitmq
////		rabbitTemplate.convertAndSend(exchange, routingKey, object);
//		Map<String,Object> map=new HashMap<String, Object>();
//		map.put("msg", "这是第一个消息");
//		map.put("data", Arrays.asList("helloworld",123,true));
//		//对象被默认序列化后发放
//		rabbitTemplate.convertAndSend("exchange.direct", "panpan.news", map);

		//// 测试发送一个对象
		Book book = new Book("作者：潘潘", "我就是我！");
		int i=0;
		while(i<10) 
		{
			i++;
			rabbitTemplate.convertAndSend("exchange.direct", "panpan.news", book);
		}
		return "发送信息";
	}
	
	/**
	 * 发送rabbit信息 (单播)
	 * 
	 * @return
	 */
	@GetMapping("/sendmsg1")
	public String sendmsg1() {
		// 发送一个消息
		//// 测试发送一个对象
		Book book = new Book("作者：潘潘", "我就是我！");
		int i=0;
		while(i<1) 
		{
			i++;
			rabbitTemplate.convertAndSend("", "atguigu", book);
		}
		return "发送信息";
	}

	/**
	 * 发送rabbit信息 (广播)
	 * 
	 * @return
	 */
	@GetMapping("/allsendmsg")
	public String allsendmsg() {
		Book book = new Book("作者：潘潘s", "我就是我s！");
		rabbitTemplate.convertAndSend("exchange.fanout", "", book);
		return "发送广播信息";
	}

	/**
	 * 接受rabbit信息
	 * 
	 * @return
	 */
	@GetMapping("/receivemsg")
	public String receivemsg() {
		// 因为我在SingleMsg自动接收了消息，所以这里已经没有消息了
//		Object o = rabbitTemplate.receiveAndConvert("panpan.news");
		Object o = rabbitTemplate.receiveAndConvert("phtest.que");
		System.out.println(o.getClass());
		System.out.println(o);

//		class java.util.HashMap
//		{msg=这是第一个消息, data=[helloworld, 123, true]}
//		转换为json

		// 发送的book对象的话
//		class com.geral.springboot06.amqp.domin.Book
//		Book(bookName=作者：潘潘, author=我就是我！)
		return "接收信息";
	}

	// AmqpAdmin:创建和删除Queue,Exchange,Bingding
	@Autowired
	AmqpAdmin amqpAdmin;

	/**
	 * 创建交换器
	 * 
	 * @return
	 */
	@GetMapping("/creatamqp")
	public String creatamqp() {
		// 创建一个自定义名字的exchange 1
//		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));

		// 创建一个队列 true表示是否持久化 2
//		amqpAdmin.declareQueue(new Queue("amqpAdmin.queuw",true));
		
		// 创建绑定规则 目的地、类型、exchange、路由键、map的参数 3
//		amqpAdmin.declareBinding(new Binding("amqpAdmin.queuw",Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqp.haha", null));

		// 删除操作
//		amqpAdmin.deleteQueue(arg0)
//		amqpAdmin.deleteExchange(arg0)

		//测试一下
//		DirectExchange xx=new DirectExchange("ph1.exchange");
//		FanoutExchange xxx=new FanoutExchange("ph1.exchange");
//		TopicExchange xxxx=new TopicExchange("ph1.exchange");
//		amqpAdmin.declareExchange(xx);
//		amqpAdmin.declareQueue(new Queue("phtest1.que",true));
//		amqpAdmin.declareBinding(new Binding("phtest1.que",Binding.DestinationType.QUEUE, "ph.exchange", "phtest1.que", null));
		return "创建一个消息队列";
	}
}
