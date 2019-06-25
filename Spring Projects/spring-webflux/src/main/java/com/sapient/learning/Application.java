package com.sapient.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sapient.learning.client.TweetClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		TweetClient tweetClient = new TweetClient();
    	tweetClient.consume();
	}
}
