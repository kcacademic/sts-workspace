package com.kchandrakant.learning.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@PropertySource(value="classpath:application.properties")
@EnableMongoRepositories("com.kchandrakant.learning")
public class MongoDBConfig extends AbstractMongoConfiguration {
	
	@Value("${mongodb.database.name}")
	private String db;

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException, java.net.UnknownHostException {
		return new MongoTemplate(new SimpleMongoDbFactory(mongoClient(), getDatabaseName()));
	}

	@Override
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient();
	}

	@Override
	protected String getDatabaseName() {
		return db;
	}

}
