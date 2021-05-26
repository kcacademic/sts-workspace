package com.kchandrakant.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.kchandrakant.learning.service.MyService;

@Configuration
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");
		
		MyService myService = context.getBean(MyService.class);
		
		myService.calculateSomething();
		
		context.close();

	}
	
}
