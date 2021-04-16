package com.ethereal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.rsocket.RSocketRequester;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		RSocketRequester rSocketRequester = context.getBean(RSocketRequester.class);

		rSocketRequester.route("greeting").data("Kumar Chandrakant").retrieveMono(String.class);
		
	}

}