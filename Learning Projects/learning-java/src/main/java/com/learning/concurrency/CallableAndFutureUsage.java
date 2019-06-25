package com.learning.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableAndFutureUsage {

	public static void blockingFuture() throws InterruptedException, ExecutionException, TimeoutException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Callable<String> callable = () -> {
			// Perform some computation
			System.out.println("Entered Callable");
			Thread.sleep(2000);
			return "Hello from Callable";
		};

		System.out.println("Submitting Callable");
		Future<String> future = executorService.submit(callable);

		// This line executes immediately
		System.out.println("Do something else while callable is getting executed");

		System.out.println("Retrieve the result of the future");
		// Future.get() blocks until the result is available
		// String result = future.get();
		String result = future.get(1, TimeUnit.SECONDS);
		System.out.println(result);

		executorService.shutdown();
	}

	public static void nonBlockingFuture() throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future<String> future = executorService.submit(() -> {
			Thread.sleep(2000);
			return "Hello from Callable";
		});

		while (!future.isDone()) {
			System.out.println("Task is still not done...");
			Thread.sleep(200);
		}

		System.out.println("Task completed! Retrieving the result");
		String result = future.get();
		System.out.println(result);

		executorService.shutdown();
	}

	public static void cancellingFuture() throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		long startTime = System.nanoTime();
		Future<String> future = executorService.submit(() -> {
			Thread.sleep(2000);
			return "Hello from Callable";
		});

		while (!future.isDone()) {
			System.out.println("Task is still not done...");
			Thread.sleep(200);
			double elapsedTimeInSec = (System.nanoTime() - startTime) / 1000000000.0;

			if (elapsedTimeInSec > 1) {
				future.cancel(true);
			}
		}

		if (!future.isCancelled()) {
			System.out.println("Task completed! Retrieving the result");
			String result = future.get();
			System.out.println(result);
		} else {
			System.out.println("Task was cancelled");
		}

		executorService.shutdown();
	}
	
	public static void multipleInvocationFutures() throws InterruptedException, ExecutionException {
		
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> task1 = () -> {
            Thread.sleep(2000);
            return "Result of Task1";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(1000);
            return "Result of Task2";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(5000);
            return "Result of Task3";
        };

        List<Callable<String>> taskList = Arrays.asList(task1, task2, task3);

        List<Future<String>> futures = executorService.invokeAll(taskList);

        for(Future<String> future: futures) {
            // The result is printed only after all the futures are complete. (i.e. after 5 seconds)
            System.out.println(future.get());
        }
        
        // Returns the result of the fastest callable. (task2 in this case)
        String result = executorService.invokeAny(Arrays.asList(task1, task2, task3));

        System.out.println(result);

        executorService.shutdown();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// blockingFuture();
		// nonBlockingFuture();
		// cancellingFuture();
		multipleInvocationFutures();

	}

}
