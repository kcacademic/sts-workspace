package com.ethereal.learning.graphql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ethereal.learning.dao.CustomerDao;
import com.ethereal.learning.domain.Customer;

@Component
public class Query implements GraphQLQueryResolver {
	
	private CustomerDao customerDao;
	
    public Query(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

	public List<Customer> getRecentCustomers(int count, int offset) {
		return customerDao.getRecentCustomers(count, offset);
	}
}