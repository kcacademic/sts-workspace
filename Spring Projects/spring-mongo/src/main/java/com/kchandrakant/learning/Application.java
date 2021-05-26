package com.kchandrakant.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kchandrakant.learning.config.MongoDBConfig;
import com.kchandrakant.learning.model.Customer;
import com.kchandrakant.learning.repository.CustomerRepository;

public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MongoDBConfig.class);

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

		// fetch an individual customer
		log.info("Customer found with findByFirstName('Alice'):");
		log.info("--------------------------------");
		log.info(repository.findByFirstName("Alice").toString());

		log.info("Customers found with findByLastName('Smith'):");
		log.info("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			log.info(customer.toString());
		}

		context.close();

	}

}
