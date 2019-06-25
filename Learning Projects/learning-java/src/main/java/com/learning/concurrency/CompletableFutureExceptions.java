package com.learning.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExceptions {

	public static void callbackExceptionScenario() {

		CompletableFuture.supplyAsync(() -> {
			// Code which might throw an exception
			return "Some result";
		}).thenApply(result -> {
			return "processed result";
		}).thenApply(result -> {
			return "result after further processing";
		}).thenAccept(result -> {
			// do something with the final result
		});

	}

	public static void exceptionallyExample() throws InterruptedException, ExecutionException {

		Integer age = -1;

		CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
			if (age < 0) {
				throw new IllegalArgumentException("Age can not be negative");
			}
			if (age > 18) {
				return "Adult";
			} else {
				return "Child";
			}
		}).exceptionally(ex -> {
			System.out.println("Oops! We have an exception - " + ex.getMessage());
			return "Unknown!";
		});

		System.out.println("Maturity : " + maturityFuture.get());
	}

	public static void handleExample() throws InterruptedException, ExecutionException {

		Integer age = -1;

		CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
			if (age < 0) {
				throw new IllegalArgumentException("Age can not be negative");
			}
			if (age > 18) {
				return "Adult";
			} else {
				return "Child";
			}
		}).handle((res, ex) -> {
			if (ex != null) {
				System.out.println("Oops! We have an exception - " + ex.getMessage());
				return "Unknown!";
			}
			return res;
		});

		System.out.println("Maturity : " + maturityFuture.get());
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// exceptionallyExample();
		handleExample();

	}

}
