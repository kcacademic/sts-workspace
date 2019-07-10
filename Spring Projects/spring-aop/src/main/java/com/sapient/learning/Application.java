package com.sapient.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.sapient.learning.service.MyService;

@Configuration
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sapient.learning");
		
		MyService myService = context.getBean(MyService.class);
		
		myService.calculateSomething();
		
		context.close();

	}
	
}
