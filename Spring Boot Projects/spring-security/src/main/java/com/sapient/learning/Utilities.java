package com.sapient.learning;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
public class Utilities {
	
	public static void main(String[] args) {
		
		//PasswordEncoder encoder = new BCryptPasswordEncoder(11, new SecureRandom("kumar".getBytes()));
		
		PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
		
		
		System.out.println(encoder.encode("password"));
		System.out.println(encoder.encode("password"));
	}

}
