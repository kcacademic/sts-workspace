package com.coc.payments.service;

import java.util.Optional;

import com.coc.payments.exception.PaymentCreationException;
import com.coc.payments.exception.PaymentExecutionException;
import com.coc.payments.exception.PaymentRecordMissingException;
import com.coc.payments.vo.PaymentData;

public interface PaymentService {

    public Optional<String> createPayment(PaymentData payment) throws PaymentCreationException;

    public Optional<String> authenticatePayment(String token, String paymentId, String payerId) throws PaymentRecordMissingException;

    public Optional<String> executePayment(String paymentId) throws PaymentRecordMissingException, PaymentExecutionException;

    public Optional<PaymentData> fetchPayment(String paymentId) throws PaymentRecordMissingException;

    public Optional<String> capturePayment(String paymentId, Float amount) throws PaymentRecordMissingException;

}
