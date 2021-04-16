package com.ethereal.learning.graphql;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.ethereal.learning.dao.CustomerDao;
import com.ethereal.learning.domain.Customer;

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