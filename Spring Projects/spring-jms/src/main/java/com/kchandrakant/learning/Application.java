package com.kchandrakant.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.kchandrakant.learning.data.Email;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));

		context.close();

	}

}