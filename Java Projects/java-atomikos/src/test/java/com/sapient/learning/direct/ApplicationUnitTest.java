package com.sapient.learning.direct;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

public class ApplicationUnitTest {

	private static DataSource inventoryDataSource;
	private static DataSource orderDataSource;

	@Test
	public void testPlaceOrder() {

		try {
			setUp();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			Application application = new Application(inventoryDataSource, orderDataSource);
			application.placeOrder("2", "1", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			verify("2", "1");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void verify(String orderId, String productId) throws Exception {

		UserTransactionImp utx = new UserTransactionImp();
		utx.begin();

		Connection inventoryConnection = inventoryDataSource.getConnection();
		Connection orderConnection = orderDataSource.getConnection();

		Statement s1 = inventoryConnection.createStatement();
		String q1 = "select balance from Inventory where id='" + productId + "'";
		ResultSet rs1 = s1.executeQuery(q1);
		if (rs1 == null || !rs1.next())
			throw new Exception("Product not found: " + productId);
		System.out.println(rs1.getLong(1));

		Statement s2 = orderConnection.createStatement();
		String q2 = "select id from Orders where id='" + orderId + "'";
		ResultSet rs2 = s2.executeQuery(q2);
		if (rs2 == null || !rs2.next())
			throw new Exception("Order not found: " + orderId);
		System.out.println(rs2.getString(1));

		inventoryConnection.close();
		orderConnection.close();

		utx.commit();

	}

	private static void setUp() throws SQLException {

		inventoryDataSource = getDataSource("db1");
		orderDataSource = getDataSource("db2");

		Connection inventoryConnection = inventoryDataSource.getConnection();
		Connection orderConnection = orderDataSource.getConnection();

		String createInventoryTable = "create table Inventory ( "
				+ " id VARCHAR ( 20 ) PRIMARY KEY, balance DECIMAL (19,0) )";
		String createInventoryRow = "insert into Inventory values ( '1', 10000 )";
		Statement s1 = inventoryConnection.createStatement();
		try {
			s1.executeUpdate(createInventoryTable);
		} catch (Exception e) {
			System.out.println("Inventory table exists");
		}
		try {
			s1.executeUpdate(createInventoryRow);
		} catch (Exception e) {
			System.out.println("Product row exists");
		}
		s1.close();

		String createOrderTable = "create table Orders ( id VARCHAR ( 20 ) PRIMARY KEY, product VARCHAR ( 20 ), balance DECIMAL (19,0) )";

		Statement s2 = orderConnection.createStatement();
		try {
			s2.executeUpdate(createOrderTable);
		} catch (Exception e) {
			System.out.println("Orders table exists");
		}
		s2.close();

		inventoryConnection.close();
		orderConnection.close();
	}

	private static DataSource getDataSource(String db) {

		DataSource ds;
		AtomikosDataSourceBean ads = new AtomikosDataSourceBean();
		ads.setXaDataSourceClassName("org.apache.derby.jdbc.EmbeddedXADataSource");
		Properties properties = new Properties();
		properties.put("databaseName", db);
		properties.put("createDatabase", "create");
		ads.setXaProperties(properties);
		ads.setUniqueResourceName(db);
		ads.setPoolSize(10); // optional
		ads.setBorrowConnectionTimeout(10); // optional
		ds = ads;
		return ds;

	}

}
