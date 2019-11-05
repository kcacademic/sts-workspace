package com.coc.payments.callback;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coc.payments.exception.PaymentRecordMissingException;
import com.coc.payments.service.PaymentService;

@RestController
public class PaypalCallbacks {

    Logger logger = LoggerFactory.getLogger(PaypalCallbacks.class);

    @Autowired
    PaymentService service;

    @GetMapping("/process")
    public ResponseEntity<String> authSuccess(@RequestParam String token, @RequestParam String paymentId, @RequestParam(name = "PayerID") String payerID) {
        if (logger.isInfoEnabled())
            logger.info(String.format("Paypal payment authentication passed with details, PaymentId: %s PayerId: %s Token: %s", paymentId, payerID, token));
        String status = "/error";
        try {
            Optional<String> optional = service.authenticatePayment(token, paymentId, payerID);
            if(optional.isPresent())
                status = optional.get();
        } catch (PaymentRecordMissingException e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(status);
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> authFailure(@RequestParam String token, HttpServletResponse response) {
        if (logger.isInfoEnabled())
            logger.info(String.format("Paypal payment authentication failed with details, Token: %s", token));
        return ResponseEntity.badRequest()
            .body("Paypal authentication failed, please try again later.");
    }

}
