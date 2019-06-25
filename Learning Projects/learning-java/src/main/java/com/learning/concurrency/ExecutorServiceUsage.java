package com.learning.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceUsage {

	public static void singleThreadExecutor() {

		System.out.println("Inside : " + Thread.currentThread().getName());

		System.out.println("Creating Executor Service...");
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		System.out.println("Creating a Runnable...");
		Runnable runnable = () -> {
			System.out.println("Inside : " + Thread.currentThread().getName());
		};

		System.out.println("Submit the task specified by the runnable to the executor service.");
		executorService.submit(runnable);

		System.out.println("Shutting down the executor");
		executorService.shutdown();
	}

	public static void multiThreadExecutor() {

		System.out.println("Inside : " + Thread.currentThread().getName());

		System.out.println("Creating Executor Service with a thread pool of Size 2");
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Runnable task1 = () -> {
			System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException ex) {
				throw new IllegalStateException(ex);
			}
		};

		Runnable task2 = () -> {
			System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException ex) {
				throw new IllegalStateException(ex);
			}
		};

		Runnable task3 = () -> {
			System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException ex) {
				throw new IllegalStateException(ex);
			}
		};

		System.out.println("Submitting the tasks for execution...");
		executorService.submit(task1);
		executorService.submit(task2);
		executorService.submit(task3);

		executorService.shutdown();

	}

	public static void scheduledExecutor() {

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
			System.out.println("Executing Task At " + System.nanoTime());
		};

		System.out.println("Submitting task at " + System.nanoTime() + " to be executed after 5 seconds.");
		
		scheduledExecutorService.schedule(task, 5, TimeUnit.SECONDS);

		scheduledExecutorService.shutdown();
	}

	public static void periodicScheduledExecuor() {
		
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
          System.out.println("Executing Task At " + System.nanoTime());
        };
        
        System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
        scheduledExecutorService.scheduleAtFixedRate(task, 0,2, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {

		// singleThreadExecutor();
		// multiThreadExecutor();
		// scheduledExecutor();
		periodicScheduledExecuor();

	}

}
