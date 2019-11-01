package com.coc.payments.callback;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coc.payments.service.PaymentService;

@RestController
public class PaypalCallbacks {

    Logger logger = LoggerFactory.getLogger(PaypalCallbacks.class);

    @Autowired
    PaymentService service;

    @GetMapping("/process")
    public ResponseEntity<String> authSuccess(@RequestParam String paymentId, @RequestParam String PayerID, @RequestParam String token) {
        logger.info("Paypal payment authentication passed with details, PaymentId: " + paymentId + " PayerId: " + PayerID + " Token: " + token);
        String status = service.executePayment(paymentId, PayerID);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> authFailure(@RequestParam String token, HttpServletResponse response) throws IOException {
        logger.info("Paypal payment authentication failed with details, Token: " + token);
        return ResponseEntity.badRequest()
            .body("Paypal authentication failed, please try again later.");
    }

}
