package com.learning.threads;

public class ThreadJoin {
	public static void main(String[] args) throws InterruptedException {

		Runnable r = () -> {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		for (int i = 0; i < 5; i++) {
			Thread t1 = new Thread(r);
			t1.start();
			t1.join();
		}

		System.out.println(Thread.currentThread().getName());
	}
}
