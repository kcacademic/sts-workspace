package com.ethereal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ethereal.learning.client.TweetClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		TweetClient tweetClient = new TweetClient();
    	tweetClient.consume();
	}
}
