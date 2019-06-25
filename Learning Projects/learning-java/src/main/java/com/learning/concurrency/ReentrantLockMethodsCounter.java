package com.learning.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockMethodsCounter {
    private final ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    public int incrementAndGet() throws InterruptedException {
        // Check if the lock is currently acquired by any thread
        System.out.println("IsLocked : " + lock.isLocked());

        // Check if the lock is acquired by the current thread itself.
        System.out.println("IsHeldByCurrentThread : " + lock.isHeldByCurrentThread());

        // Try to acquire the lock
        // boolean isAcquired = lock.tryLock();
        boolean isAcquired = lock.tryLock(1, TimeUnit.SECONDS);
        System.out.println("Lock Acquired : " + isAcquired + "\n");

        if(isAcquired) {
            try {
                Thread.sleep(2000);
                count = count + 1;
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                lock.unlock();
            }
        }
        return count;
    }
}