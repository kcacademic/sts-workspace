package com.sapient.learning.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.sapient.learning.domain.Customer;

public class CustomerDao {
	
    private List<Customer> customers;

    public CustomerDao(List<Customer> customers) {
        this.customers = customers;
    }
    
    public void saveCustomer(Customer customer) {
    	customers.add(0, customer);
    }
    
    public List<Customer> getRecentCustomers(int count, int offset) {
        return customers.stream().skip(offset).limit(count).collect(Collectors.toList());
    }

}
