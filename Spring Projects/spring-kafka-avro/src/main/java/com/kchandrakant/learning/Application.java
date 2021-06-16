package com.kchandrakant.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

import com.kchandrakant.learning.User;

public class Application {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");

		KafkaTemplate<String, User> kafkaTemplate = context.getBean(KafkaTemplate.class);
		
		User user = new User("Tom Hardy", 26);
		
		kafkaTemplate.send("users", user.getName(), user);

		Thread.sleep(10000);

		context.close();
	}

}
