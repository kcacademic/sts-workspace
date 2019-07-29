package com.sapient.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sapient.learning");

		Thread.sleep(1000);

		context.close();

	}
	

}