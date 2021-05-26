package com.kchandrakant.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");

		Thread.sleep(1000);

		context.close();

	}
	

}