package com.sapient.learning.direct;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import com.atomikos.icatch.jta.UserTransactionImp;

public class Application {

	private DataSource inventoryDataSource;
	private DataSource orderDataSource;

	public Application(DataSource inventoryDataSource, DataSource orderDataSource) {

		this.inventoryDataSource = inventoryDataSource;
		this.orderDataSource = orderDataSource;
	}

	public void placeOrder(String orderId, String productId, int amount) throws Exception {

		UserTransactionImp utx = new UserTransactionImp();

		boolean rollback = false;
		try {
			// begin a transaction
			utx.begin();
			// access the datasource and do any JDBC you like
			Connection inventoryConnection = inventoryDataSource.getConnection();
			Connection orderConnection = orderDataSource.getConnection();

			// perform operations on inventory database
			Statement s1 = inventoryConnection.createStatement();
			String q1 = "update Inventory set balance = balance - " + amount + " where id ='" + productId + "'";
			s1.executeUpdate(q1);
			s1.close();

			// perform operations on order database
			Statement s2 = orderConnection.createStatement();
			String q2 = "insert into Orders values ( '" + orderId + "', '" + productId + "', " + amount + " )";
			s2.executeUpdate(q2);
			s2.close();

			inventoryConnection.close();
			orderConnection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			rollback = true;
		} finally {
			if (!rollback)
				utx.commit();
			else
				utx.rollback();
		}
	}

}
