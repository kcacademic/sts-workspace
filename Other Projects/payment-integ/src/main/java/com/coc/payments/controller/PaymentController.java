package com.coc.payments.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coc.payments.exception.PaymentCreationException;
import com.coc.payments.service.PaymentService;
import com.coc.payments.vo.PaymentData;
import com.coc.payments.vo.PaymentRequest;

@RestController
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    PaymentService service;

    @PostMapping("/payments/paypal")
    public String createPayment(@RequestBody PaymentRequest request) {
        PaymentData payment = new PaymentData();
        payment.setIdempotencyKey(request.getIdempotencyKey());
        payment.setUserId(request.getUserId());
        payment.setPaymentProvider(request.getPaymentProvider());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setIntent(request.getIntent());
        payment.setDescription(request.getDescription());
        payment.setCurrency(request.getCurrency());
        payment.setTotal(request.getTotal());
        payment.setSubTotal(request.getSubTotal());
        payment.setShipping(request.getShipping());
        payment.setTax(request.getTax());

        String authUrl = null;
        try {
            Optional<String> optional = service.createPayment(payment);
            if (optional.isPresent())
                authUrl = optional.get();
        } catch (PaymentCreationException e) {
            logger.error(e.getMessage());
        }
        return authUrl;
    }

}
