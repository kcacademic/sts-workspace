package com.coc.payments.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coc.payments.service.PaymentService;
import com.coc.payments.vo.PaymentData;
import com.coc.payments.vo.PaymentRequest;

@RestController
public class PaymentController {

    @Autowired
    PaymentService service;

    @PostMapping("/payments/paypal")
    public String createPayment(@RequestBody PaymentRequest request) throws IOException {
        PaymentData payment = new PaymentData();
        payment.setUserId(request.getUserId());
        String redirectUrl = service.createPayment(payment);
        return redirectUrl;
    }

}
