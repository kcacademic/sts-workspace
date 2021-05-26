package com.kchandrakant.learning.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    @JmsListener(destination = "TEST.FOO")
    public void handleMessage(String message) {
        System.out.println("Message Received: " + message);
    }
}