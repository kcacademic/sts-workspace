package com.sapient.learning;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.UnknownHostException;
import java.util.UUID;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.sapient.learning.adaptor.CustomerAdaptor;
import com.sapient.learning.model.Customer;

@TestMethodOrder(OrderAnnotation.class)
public class MongoDBTests {

	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> collection;
	private static UUID uuid = UUID.randomUUID();

	@BeforeAll
	public static void setUp() throws UnknownHostException {
		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		database = mongoClient.getDatabase("test");
		collection = database.getCollection("customer");
	}

	@AfterAll
	public static void tearDown() {
		database.drop();
		mongoClient.close();
	}

	@Test
	@Order(1)
	public void insertDocument() {
		collection.insertOne(
				CustomerAdaptor.toDocument(
						new Customer(uuid, "Kumar", "Chandrakant")));
	}
	
	@Test
	@Order(2)
	public void fetchDocument() {
		FindIterable<Document> result = collection.find(eq("_id", uuid));
		Document document = result.first();
		assertEquals(document.get("_id"), uuid);
	}
	
	@Test
	@Order(3)
	public void deleteDocument() {
		DeleteResult result = collection.deleteOne(eq("_id", uuid));
		assertEquals(result.getDeletedCount(), 1);
	}

}
