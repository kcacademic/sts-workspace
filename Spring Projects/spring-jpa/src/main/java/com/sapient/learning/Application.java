package com.sapient.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sapient.learning.config.HibernateConfig;
import com.sapient.learning.model.Customer;
import com.sapient.learning.model.Order;
import com.sapient.learning.repository.CustomerRepository;
import com.sapient.learning.repository.OrderRepository;

public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

		OrderRepository orderRepository = context.getBean(OrderRepository.class);
		
		// create a customer and couple of orders
		Customer customer = new Customer("Jack", "Bauer");
		Order firstOrder = new Order("Card", "Office", customer);
		Order secondOrder = new Order("Cash", "Home", customer);

		// save the customer and orders
		customerRepository.save(customer);
		orderRepository.save(firstOrder);
		orderRepository.save(secondOrder);

		// fetch customers by last name
		log.info("Customer found with findByLastName('Bauer'):");
		log.info("--------------------------------------------");
		customerRepository.findByLastName("Bauer").forEach(c -> {
			log.info(c.toString());
		});
		
		context.close();

	}

}