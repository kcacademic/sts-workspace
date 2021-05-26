package com.kchandrakant.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kchandrakant.learning.config.CassandraConfig;
import com.kchandrakant.learning.model.Customer;
import com.kchandrakant.learning.repository.CustomerRepository;

public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CassandraConfig.class);

		CustomerRepository repository = context.getBean(CustomerRepository.class);

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer customer : repository.findAll()) {
			log.info(customer.toString());
		}
		
		context.close();
	}

}
