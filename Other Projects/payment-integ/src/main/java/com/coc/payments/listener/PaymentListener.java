package com.coc.payments.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.coc.payments.event.PaymentEvent;
import com.coc.payments.service.PaymentService;

@Service
public class PaymentListener {
    
    private static final String PAYMENT_AUTHENTICATED = "authenticated";

    Logger logger = LoggerFactory.getLogger(PaymentListener.class);

    @Autowired
    PaymentService paypalService;

    @KafkaListener(topics = "coc_payments", groupId = "coc_consumer")
    public void listen(PaymentEvent event) {
        if (logger.isInfoEnabled())
            logger.info(String.format("Received Messasge in group - coc_consumer: %s", event));
        if (PAYMENT_AUTHENTICATED.equals(event.getType())) {
            try {
                paypalService.executePayment(event.getId());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

}
