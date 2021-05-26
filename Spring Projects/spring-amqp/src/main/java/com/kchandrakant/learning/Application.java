package com.kchandrakant.learning;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kchandrakant.learning.data.Message;

public class Application {

	public static void main(String[] args) throws InterruptedException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");

		AmqpTemplate template = context.getBean(RabbitTemplate.class);
		Message message = new Message();
		message.setKey("Kumar Chandrakant");
		template.convertAndSend("foo.bar", message);
		Thread.sleep(1000);

		context.close();

	}

}