package com.sapient.learning;

import java.util.concurrent.ExecutionException;

import com.sapient.learning.kafka.MyKafkaProducer;

public class Main {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		
		MyKafkaProducer myKafkaProducer = new MyKafkaProducer();
		myKafkaProducer.send("Kumar Chandrakant");
		
	}

}
