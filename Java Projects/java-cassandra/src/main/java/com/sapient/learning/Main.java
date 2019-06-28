package com.sapient.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import com.sapient.learning.connector.CassandraConnector;
import com.sapient.learning.model.Customer;
import com.sapient.learning.repository.CustomerRepository;
import com.sapient.learning.repository.KeyspaceRepository;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws InterruptedException {

		CassandraConnector connector = new CassandraConnector();
		connector.connect("127.0.0.1", null);
		Session session = connector.getSession();

		KeyspaceRepository schema = new KeyspaceRepository(session);
		schema.createKeyspace("shop", "SimpleStrategy", 1);
		schema.useKeyspace("shop");

		CustomerRepository repository = new CustomerRepository(session);
		repository.createTableCustomer();
		repository.createTableCustomerByFirstName();

		Customer customer = new Customer(UUIDs.timeBased(), "Kumar", "Chandrakant");
		repository.insertCustomer(customer);

		repository.selectAll().forEach(o -> LOG.info("First Name: " + o.getFirstName()));
		repository.selectAllByFirstName("Kumar").forEach(o -> LOG.info("First Name: " + o.getFirstName()));

		repository.deleteTableCustomer();
		repository.deleteTableCustomerByFirstname();
		schema.deleteKeyspace("shop");

		connector.close();
	}

}