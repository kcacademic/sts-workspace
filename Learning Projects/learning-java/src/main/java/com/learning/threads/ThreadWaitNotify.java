package com.learning.threads;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadWaitNotify {

	public static void main(String args[]) {

		final Queue<Integer> sharedQ = new LinkedList<>();

		Thread producer = new Producer(sharedQ);
		Thread consumer = new Consumer(sharedQ);

		producer.start();
		consumer.start();

	}
}