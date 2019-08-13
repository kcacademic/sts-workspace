package com.sapient.learning.graphql;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.sapient.learning.dao.CustomerDao;
import com.sapient.learning.domain.Customer;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

	private CustomerDao customerDao;

	public Subscription(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
    public Publisher<Customer> getNewCustomer() {
        return customerDao.getNewCustomer();
    }
}