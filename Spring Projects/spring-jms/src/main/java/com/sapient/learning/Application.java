package com.sapient.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.sapient.learning.data.Email;

@SpringBootApplication
@EnableJms
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(JmsTemplate jmsTemplate) {
		return (args) -> {
			log.info("Sending an email message.");
			jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
		};
	}

}