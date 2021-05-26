package com.kchandrakant.learning;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) throws InterruptedException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kchandrakant.learning");
		
	    context.registerShutdownHook();
	     
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Please enter q and press <enter> to exit the program: ");
	     
	    while (true) {
	       String input = scanner.nextLine();
	       if("q".equals(input.trim())) {
	          break;
	      }
	    }
	    System.exit(0);

	    scanner.close();
		context.close();

	}

}