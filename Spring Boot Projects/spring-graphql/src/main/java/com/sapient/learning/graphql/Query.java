package com.sapient.learning.graphql;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.sapient.learning.dao.CustomerDao;
import com.sapient.learning.domain.Customer;

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