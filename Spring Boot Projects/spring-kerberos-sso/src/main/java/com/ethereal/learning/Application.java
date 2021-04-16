package com.ethereal.learning;

import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	static {
		System.setProperty("java.security.krb5.conf",
				Paths.get("C:\\Users\\kumchand0\\Apps\\sts-workspace\\Spring Projects\\spring-minikdc\\kdc-1\\krb5.conf")
						.normalize().toAbsolutePath().toString());
		System.setProperty("sun.security.krb5.debug", "true");
	}

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
