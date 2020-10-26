package com.baeldung.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baeldung.springboot.config.SpringBootConfig;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	SpringBootConfig springBootConfig;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... args) {

		System.out.println(springBootConfig.getValueFromString());
		System.out.println(springBootConfig.getMap());
		System.out.println(springBootConfig.getAnotherMap());

	}

}
