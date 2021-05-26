package com.kchandrakant.learning;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kchandrakant.learning.config.MyRoute;

public class Application {

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");
		
		RouteBuilder routeBuilder = context.getBean(MyRoute.class);
		
		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(routeBuilder);
		camelContext.start();
		
		Thread.sleep(5000);
		
		camelContext.stop();
		context.close();
	}
	
}
