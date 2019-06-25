package com.learning.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyIssues {

	public static void raceCondition() throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Counter counter = new Counter();

		for (int i = 0; i < 1000; i++) {
			executorService.submit(() -> counter.increment());
		}

		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);

		System.out.println("Final count is : " + counter.getCount());
	}

	public static void raceConditionAvoided() throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

		for (int i = 0; i < 1000; i++) {
			executorService.submit(() -> synchronizedCounter.increment());
		}

		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);

		System.out.println("Final count is : " + synchronizedCounter.getCount());
	}

	private static boolean sayHello = false;

	public static void memoryInconsistency() throws InterruptedException {

		Thread thread = new Thread(() -> {
			while (!sayHello) {
			}

			System.out.println("Hello World!");

			while (sayHello) {
			}

			System.out.println("Good Bye!");
		});

		thread.start();

		Thread.sleep(1000);
		System.out.println("Say Hello..");
		sayHello = true;

		Thread.sleep(1000);
		System.out.println("Say Bye..");
		sayHello = false;
	}
	
	private static volatile boolean sayCheese = false;

	public static void memoryInconsistencyAvoided() throws InterruptedException {

		Thread thread = new Thread(() -> {
			while (!sayCheese) {
			}

			System.out.println("Hello World!");

			while (sayCheese) {
			}

			System.out.println("Good Bye!");
		});

		thread.start();

		Thread.sleep(1000);
		System.out.println("Say Hello..");
		sayCheese = true;

		Thread.sleep(1000);
		System.out.println("Say Bye..");
		sayCheese = false;
	}

	public static void main(String[] args) throws InterruptedException {

		// raceCondition();
		// raceConditionAvoided();
		// memoryInconsistency();
		memoryInconsistencyAvoided();

	}

}
