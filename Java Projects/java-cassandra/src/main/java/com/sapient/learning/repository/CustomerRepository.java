package com.sapient.learning.repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.sapient.learning.model.Customer;

public class CustomerRepository {

	public static final String TABLE_NAME = "customers";
	
	public static final String TABLE_NAME_BY_FIRST_NAME = TABLE_NAME + "ByFirstName";

	private Session session;

	public CustomerRepository(Session session) {
		this.session = session;
	}

	public void createTableCustomer() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append(TABLE_NAME)
				.append("(")
				.append("id uuid PRIMARY KEY, ")
				.append("firstName text,")
				.append("lastName text")
				.append(");");

		final String query = sb.toString();
		session.execute(query);
	}
	
    public void createTableCustomerByFirstName() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
        		.append(TABLE_NAME_BY_FIRST_NAME)
        		.append("(")
        		.append("id uuid, ")
        		.append("firstName text,")
        		.append("PRIMARY KEY (firstName, id)")
        		.append(");");

        final String query = sb.toString();
        session.execute(query);
    }

	public void insertCustomer(Customer customer) {
        StringBuilder sb = new StringBuilder("BEGIN BATCH ")
        		.append("INSERT INTO ")
        		.append(TABLE_NAME)
        		.append("(id, firstName, lastName) ")
        		.append("VALUES (").append(customer.getId()).append(", '").append(customer.getFirstName()).append("', '").append(customer.getLastName()).append("');")
        		.append("INSERT INTO ")
        		.append(TABLE_NAME_BY_FIRST_NAME).append("(id, firstName) ").append("VALUES (").append(customer.getId()).append(", '").append(customer.getFirstName()).append("');")
                .append("APPLY BATCH;");

        final String query = sb.toString();
        session.execute(query);
	}
	
	public List<Customer> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);

		final String query = sb.toString();
		ResultSet rs = session.execute(query);

		List<Customer> customers = new ArrayList<Customer>();

		for (Row r : rs) {
			Customer book = new Customer(r.getUUID("id"), r.getString("firstName"), r.getString("lastName"));
			customers.add(book);
		}
		return customers;
	}

	public List<Customer> selectAllByFirstName(String firstName) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ")
				.append(TABLE_NAME_BY_FIRST_NAME)
				.append(" WHERE firstName = '")
				.append(firstName)
				.append("';");

		final String query = sb.toString();

		ResultSet rs = session.execute(query);

		List<Customer> customers = new ArrayList<Customer>();

		for (Row r : rs) {
			Customer s = new Customer(r.getUUID("id"), r.getString("firstName"), null);
			customers.add(s);
		}

		return customers;
	}
	
    public void deleteTableCustomer() {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(TABLE_NAME);

        final String query = sb.toString();
        session.execute(query);
    }
    
    public void deleteTableCustomerByFirstname() {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(TABLE_NAME_BY_FIRST_NAME);

        final String query = sb.toString();
        session.execute(query);
    }
    
}