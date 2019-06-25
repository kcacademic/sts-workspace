package com.learning.threads;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWait {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Runnable r = () -> {
            synchronized (lock) {
                while (true) {
                    System.out.println(Thread.currentThread()
                        .getName());
                    try {
                        lock.notify();
                        lock.wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(r).start();
        new Thread(r).start();
    }
}
