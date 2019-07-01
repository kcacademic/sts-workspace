package com.sapient.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.sapient.learning.data.Email;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sapient.learning");

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));

		context.close();

	}

}