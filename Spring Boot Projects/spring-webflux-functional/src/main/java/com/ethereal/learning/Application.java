package com.ethereal.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ethereal.learning.client.EmployeeClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		EmployeeClient employeeClient = new EmployeeClient();
		employeeClient.consume();
	}
}