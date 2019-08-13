package com.sapient.learning.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import com.sapient.learning.domain.Customer;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

@Component
public class CustomerDao {

	private List<Customer> customers;
	
    final FluxProcessor<Customer, Customer> processor;
    final FluxSink<Customer> sink;

	public CustomerDao() {
		customers = new ArrayList<>();
		for (int custId = 0; custId < 10; ++custId) {
			Customer customer = new Customer();
			customer.setId(String.valueOf(custId));
			customer.setFirstName("FirstName");
			customer.setLastName("LastName");
			customers.add(customer);
		}
        this.processor = DirectProcessor.<Customer>create().serialize();
        this.sink = processor.sink();
	}

	public void saveCustomer(Customer customer) {
		customers.add(0, customer);
		sink.next(customer);
	}

	public List<Customer> getRecentCustomers(int count, int offset) {
		return customers.stream().skip(offset).limit(count).collect(Collectors.toList());
	}

	public Publisher<Customer> getNewCustomer() {
		return processor;
	}

}
