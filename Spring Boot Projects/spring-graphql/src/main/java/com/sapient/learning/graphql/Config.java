package com.sapient.learning.graphql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sapient.learning.dao.CustomerDao;
import com.sapient.learning.domain.Customer;

@Configuration
public class Config {

	@Bean
	public CustomerDao customerDao() {
		List<Customer> customers = new ArrayList<>();
		for (int custId = 0; custId < 10; ++custId) {
			Customer customer = new Customer();
			customer.setId(String.valueOf(custId));
			customer.setFirstName("");
			customer.setLastName("");
			customers.add(customer);
		}
		return new CustomerDao(customers);
	}

	@Bean
	public Query query(CustomerDao customerDao) {
		return new Query(customerDao);
	}

	@Bean
	public Mutation mutation(CustomerDao customerDao) {
		return new Mutation(customerDao);
	}

}
