package com.sapient.learning.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.sapient.learning.data.Email;
import com.sapient.learning.model.Customer;
import com.sapient.learning.repository.CustomerRepository;

@Service
public class FullfillmentService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	JmsTemplate jmsTemplate;

	@Transactional
	public void fullfillRequest(Customer customer, Email email) {

		customerRepository.save(customer);
		jmsTemplate.convertAndSend("mailbox", email);

	}

}
