package com.sapient.learning;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import com.sapient.learning.data.Email;
import com.sapient.learning.model.Customer;
import com.sapient.learning.repository.CustomerRepository;
import com.sapient.learning.service.FullfillmentService;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(FullfillmentService service, CustomerRepository customerRepository, JmsTemplate jmsTemplate) {
		return (args) -> {
			Customer customer = new Customer("Kumar", "Chandrakant");
			Email email = new Email("info@example.com", "Hello");
			try {
				service.fullfillRequest(customer, email);
			} catch (Exception ex) {

			}

			List<Customer> customers = new ArrayList<Customer>();
			customerRepository.findAll().iterator().forEachRemaining(customers::add);
			log.info("Customer Size: " + customers.size());
			jmsTemplate.setReceiveTimeout(1000);
			Email emailReceived = (Email)jmsTemplate.receiveAndConvert("mailbox");
			log.info("Email Received: " + emailReceived);
			
		};
	}

}