package com.kchandrakant.learning;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.kchandrakant.learning.service.MyService;

@Configuration
public class Application {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");
		
		MyService myService = context.getBean(MyService.class);
		
		Future<String> future = myService.calculateSomething();
		
		logger.info("Method Called");
		
	    while (true) {
	        if (future.isDone()) {
	        	logger.info("Result from asynchronous process - " + future.get());
	            break;
	        }
	        logger.info("Continue doing something else. ");
	        Thread.sleep(1000);
	    }
		
		context.close();

	}
	
}
