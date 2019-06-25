package com.learning.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureUsage {

	public static void runAsyncExample() throws InterruptedException, ExecutionException {

		// Using Lambda Expression
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			// Simulate a long-running Job
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			System.out.println("I'll run in a separate thread than the main thread.");
		});

		// Block and wait for the future to complete
		future.get();

	}

	public static void supplyAsyncExample() throws InterruptedException, ExecutionException {

		// Using Lambda Expression
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		});

		// Block and get the result of the Future
		String result = future.get();
		System.out.println(result);
	}

	public static void supplyAsyncWithExecutorExample() throws InterruptedException, ExecutionException {

		Executor executor = Executors.newFixedThreadPool(10);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		}, executor);

		// Block and get the result of the Future
		String result = future.get();
		System.out.println(result);

	}

	public static void thenApplyExample() throws InterruptedException, ExecutionException {

		// Create a CompletableFuture
		CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Kumar";
		});

		// Attach a callback to the Future using thenApply()
		CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
			return "Hello " + name;
		});

		// Block and get the result of the future.
		System.out.println(greetingFuture.get());
	}

	public static void thenApplyChainingExample() throws InterruptedException, ExecutionException {

		CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Kumar";
		}).thenApply(name -> {
			return "Hello " + name;
		}).thenApply(greeting -> {
			return greeting + ", Welcome to Sapient";
		});

		System.out.println(welcomeText.get());
	}

	public static void thenAcceptExample() {

		// thenAccept() example
		CompletableFuture.supplyAsync(() -> {
			return "Kumar";
		}).thenAccept(name -> {
			System.out.println("Hello " + name);
		});

	}

	public static void thenRunExample() {

		// thenRun() example
		CompletableFuture.supplyAsync(() -> {
			return "Kumar";
		}).thenRun(() -> {
			System.out.println("In the block thenRun()");
		});
	}

	public static void thenApplyAsyncWithExecutorExample() throws InterruptedException, ExecutionException {

		Executor executor = Executors.newFixedThreadPool(2);

		CompletableFuture<String> text = CompletableFuture.supplyAsync(() -> {
			return "Some result";
		}).thenApplyAsync(result -> {
			// Executed in a thread obtained from the executor
			return "Processed Result " + result;
		}, executor);

		System.out.println(text.get());
	}

	private static CompletableFuture<String> getUserDetail(String userId) {
		return CompletableFuture.supplyAsync(() -> {
			return "User " + userId;
		});
	}

	private static CompletableFuture<String> getCreditRating(String user) {
		return CompletableFuture.supplyAsync(() -> {
			return user + "Credit Rating";
		});
	}

	public static void thenComposeExample() throws InterruptedException, ExecutionException {

		CompletableFuture<String> result = getUserDetail("Kumar").thenCompose(user -> getCreditRating(user));

		System.out.println(result.get());
	}

	public static void thenCombineExample() throws InterruptedException, ExecutionException {

		System.out.println("Retrieving weight.");
		CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return 65.0;
		});

		System.out.println("Retrieving height.");
		CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return 177.8;
		});

		System.out.println("Calculating BMI.");
		CompletableFuture<Double> combinedFuture = weightInKgFuture.thenCombine(heightInCmFuture,
				(weightInKg, heightInCm) -> {
					Double heightInMeter = heightInCm / 100;
					return weightInKg / (heightInMeter * heightInMeter);
				});

		System.out.println("Your BMI is - " + combinedFuture.get());
	}

	private static CompletableFuture<String> downloadWebPage(String pageLink) {
		return CompletableFuture.supplyAsync(() -> {
			return "Webpage content: CompletableFuture";
		});
	}

	public static void allOfExample() throws InterruptedException, ExecutionException {

		List<String> webPageLinks = Arrays.asList("1", "2"); // A list of 100 web page links

		// Download contents of all the web pages asynchronously
		List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
				.map(webPageLink -> downloadWebPage(webPageLink)).collect(Collectors.toList());

		// Create a combined Future using allOf()
		CompletableFuture<Void> allFutures = CompletableFuture
				.allOf(pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()]));

		// When all the Futures are completed, call `future.join()` to get their results
		// and collect the results in a list -
		CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
			return pageContentFutures.stream().map(pageContentFuture -> pageContentFuture.join())
					.collect(Collectors.toList());
		});

		// Count the number of web pages having the "CompletableFuture" keyword.
		CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
			return pageContents.stream().filter(pageContent -> pageContent.contains("CompletableFuture")).count();
		});

		System.out.println("Number of Web Pages having CompletableFuture keyword - " + countFuture.get());

	}

	public static void anyOfExample() throws InterruptedException, ExecutionException {

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of Future 1";
		});

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of Future 2";
		});

		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of Future 3";
		});

		CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

		System.out.println(anyOfFuture.get()); // Result of Future 2
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// runAsyncExample();
		// supplyAsyncExample();
		// supplyAsyncWithExecutorExample();
		// thenApplyExample();
		// thenApplyChainingExample();
		// thenAcceptExample();
		// thenRunExample();
		// thenApplyAsyncWithExecutorExample();
		// thenComposeExample();
		// thenCombineExample();
		// allOfExample();
		anyOfExample();

	}

}
