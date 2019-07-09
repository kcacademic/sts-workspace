package com.sapient.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.ogm.session.Session;

import com.sapient.learning.model.Customer;
import com.sapient.learning.service.CustomerService;
import com.sapient.learning.session.Neo4jSessionFactory;

public class Neo4jTests {

	private static CustomerService customerService;

	@BeforeAll
	public static void setUp() {

		Session session = Neo4jSessionFactory.getInstance().getEmbeddedSession();
		customerService = new CustomerService(session);

	}

	@Test
	public void testFetchData() {

		customerService.createOrUpdate(new Customer(1L, "Kumar", "Chandrakant"));
		Customer result = customerService.find(1L);
		assertEquals(1L, result.getId());
	}

}
