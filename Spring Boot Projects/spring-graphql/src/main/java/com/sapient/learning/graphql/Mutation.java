package com.sapient.learning.graphql;

import java.util.UUID;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.sapient.learning.dao.CustomerDao;
import com.sapient.learning.domain.Customer;

public class Mutation implements GraphQLMutationResolver {
	private CustomerDao customerDao;

	public Mutation(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public Customer writeCustomer(String firstName, String lastName) {
		Customer customer = new Customer();
		customer.setId(UUID.randomUUID().toString());
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customerDao.saveCustomer(customer);
		return customer;
	}
}
