package com.sapient.learning;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.sapient.learning.model.Customer;

public class Main {

	public static void main(String[] args) throws UnknownHostException {

		final Morphia morphia = new Morphia();
		
		morphia.mapPackage("com.sapient.learning");
		
		final Datastore datastore = morphia.createDatastore(
				new MongoClient(), "test");
		datastore.ensureIndexes();
		
		final Customer customer = new Customer(UUID.randomUUID(), "Kumar", "Chandrakant");
		datastore.save(customer);
		
		final Query<Customer> query = datastore.createQuery(Customer.class);
		final List<Customer> employees = query.asList();
		System.out.println(employees);
		
		WriteResult result = datastore.delete(query);
		System.out.println(result.getN());

	}

}