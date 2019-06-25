package com.learning.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocksUsage {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		ReentrantLockMethodsCounter lockMethodsCounter = new ReentrantLockMethodsCounter();

		executorService.submit(() -> {
			try {
				System.out.println("IncrementCount (First Thread) : " + lockMethodsCounter.incrementAndGet() + "\n");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		executorService.submit(() -> {
			try {
				System.out.println("IncrementCount (Second Thread) : " + lockMethodsCounter.incrementAndGet() + "\n");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		executorService.shutdown();
	}

}
