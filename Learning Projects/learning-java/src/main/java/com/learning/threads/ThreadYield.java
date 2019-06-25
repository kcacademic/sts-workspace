package com.learning.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreadYield {

    private List<String> list = Collections.synchronizedList(new ArrayList<String>());
    
    public List<String> getList(){
        return this.list;
    }
    public void executeYieldingThreads(List<String> names, int count) {
        Runnable r = () -> {
            int counter = 0;
            while (counter < count) {
                System.out.println(Thread.currentThread()
                    .getName());
                getList().add(Thread.currentThread()
                    .getName());
                counter++;
                Thread.yield();
            }
        };
        List<Thread> threads = new ArrayList<Thread>();
        for(String name : names) {
            Thread t = new Thread(r, name);
            threads.add(t);
            t.start();
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        ThreadYield ty = new ThreadYield();
        List<String> names = Arrays.asList("Jakarta", "Crescent");
        ty.executeYieldingThreads(names, 2);
        System.out.println(ty.getList());
        
    }
}

