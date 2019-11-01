package com.coc.payments.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.coc.payments.event.PaymentEvent;

@Service
public class PaymentListener {

    @KafkaListener(topics = "coc_payments", groupId = "coc_consumer")
    public void listen(PaymentEvent message) {

        System.out.println("Received Messasge in group - coc_consumer: " + message);
    }
    
}
