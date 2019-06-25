package com.sapient.learning;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class RefinedProducerConsumerExample {
    public static void main(String[] args) {
        BlockingQueue<String> drop =
            new SynchronousQueue<String> ();
        (new Thread(new RefinedProducer(drop))).start();
        (new Thread(new RefinedConsumer(drop))).start();
    }
}