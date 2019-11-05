package com.coc.payments.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.coc.payments.domain.Operation;
import com.coc.payments.domain.PaymentRecord;
import com.coc.payments.domain.PaymentRequest;
import com.coc.payments.event.PaymentEvent;
import com.coc.payments.exception.PaymentCreationException;
import com.coc.payments.exception.PaymentExecutionException;
import com.coc.payments.exception.PaymentRecordMissingException;
import com.coc.payments.integration.PaypalIntegration;
import com.coc.payments.repository.PaymentRecordRepository;
import com.coc.payments.repository.PaymentRequestRepository;
import com.coc.payments.service.PaymentService;
import com.coc.payments.vo.PaymentData;

@Service
@PropertySource(value = "classpath:application.yml")
public class PaypalPaymentService implements PaymentService {

    private static final String TRANSACTION_FAILED = "failed";
    private static final String PAYMENT_CREATED = "created";
    private static final String PAYMENT_AUTHENTICATED = "authenticated";
    private static final String PAYMENT_EXECUTED = "executed";

    Logger logger = LoggerFactory.getLogger(PaypalPaymentService.class);

    @Autowired
    private PaypalIntegration paypalInteg;

    @Autowired
    private PaymentRecordRepository recordRepository;

    @Autowired
    private PaymentRequestRepository requestRepository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> broker;

    @Value("${payments.kafka.topic}")
    private String topic;

    @Override
    public Optional<String> createPayment(PaymentData paymentData) throws PaymentCreationException {

        Optional<PaymentRequest> requestOptional = requestRepository.findById(paymentData.getIdempotencyKey());
        PaymentRequest request = null;
        if (requestOptional.isPresent()) {
            request = requestOptional.get();
            if (PAYMENT_AUTHENTICATED.equals(request.getPaymentStatus()) || PAYMENT_EXECUTED.equals(request.getPaymentStatus()))
                throw new PaymentCreationException(String.format("Payment with the key %s has already been processed.", paymentData.getIdempotencyKey()));
            return Optional.of(requestOptional.get()
                .getAuthUrl());
        }

        PaymentData paymentResponse = paypalInteg.createPayment(paymentData);

        if (TRANSACTION_FAILED.equals(paymentResponse.getState()))
            throw new PaymentCreationException(String.format("Payment Creation Failed for id %s", paymentData.getIdempotencyKey()));

        Operation operation = new Operation();
        operation.setId(UUID.randomUUID());
        operation.setType(PAYMENT_CREATED);
        PaymentRecord record = new PaymentRecord();
        record.setId(paymentResponse.getId());
        record.setIdempotencyKey(paymentData.getIdempotencyKey());
        record.setIntent(paymentData.getIntent());
        record.setPaymentStatus(PAYMENT_CREATED);
        record.setPaymentProvider(paymentData.getPaymentProvider());
        record.setPaymentMethod(paymentData.getPaymentMethod());
        record.setDescription(paymentData.getDescription());
        record.setUserId(paymentData.getUserId());
        record.setCurrency(paymentData.getCurrency());
        record.setTotal(paymentData.getTotal());
        record.setSubTotal(paymentData.getSubTotal());
        record.setShipping(paymentData.getShipping());
        record.setTax(paymentData.getTax());
        record.getTransactions()
            .add(operation);
        recordRepository.save(record);

        request = new PaymentRequest();
        request.setIdempotencyKey(paymentData.getIdempotencyKey());
        request.setPaymentId(paymentResponse.getId());
        request.setPaymentStatus(record.getPaymentStatus());
        request.setAuthUrl(paymentResponse.getAuthUrl());
        requestRepository.save(request);

        PaymentEvent event = new PaymentEvent();
        event.setId(paymentResponse.getId());
        event.setType(PAYMENT_CREATED);
        broker.send(topic, event);

        return Optional.ofNullable(paymentResponse.getAuthUrl());
    }

    @Override
    public Optional<PaymentData> fetchPayment(String paymentId) {
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<String> authenticatePayment(String token, String paymentId, String payerId) throws PaymentRecordMissingException {

        Optional<PaymentRecord> paymentOptional = recordRepository.findById(paymentId);
        if (!paymentOptional.isPresent())
            throw new PaymentRecordMissingException(String.format("Payment Record Missing with ID %s", paymentId));

        PaymentRecord record = paymentOptional.get();
        PaymentRecord savedRecord = null;

        if (PAYMENT_CREATED.equals(record.getPaymentStatus())) {
            Operation operation = new Operation();
            operation.setId(UUID.randomUUID());
            operation.setType(PAYMENT_AUTHENTICATED);
            record.setPayerId(payerId);
            record.setPaymentStatus(PAYMENT_AUTHENTICATED);
            record.getTransactions()
                .add(operation);
            savedRecord = recordRepository.save(record);

            Optional<PaymentRequest> requestOptional = requestRepository.findById(record.getIdempotencyKey());
            if (requestOptional.isPresent()) {
                PaymentRequest request = requestOptional.get();
                request.setPaymentStatus(PAYMENT_AUTHENTICATED);
                requestRepository.save(request);
            }
        }

        PaymentEvent event = new PaymentEvent();
        event.setId(paymentId);
        event.setType(PAYMENT_AUTHENTICATED);
        broker.send(topic, event);

        return Optional.ofNullable(savedRecord != null ? savedRecord.getId() : null);
    }

    @Override
    public Optional<String> capturePayment(String paymentId, Float amount) {
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<String> executePayment(String id) throws PaymentRecordMissingException, PaymentExecutionException {

        Optional<PaymentRecord> paymentOptional = recordRepository.findById(id);
        if (!paymentOptional.isPresent())
            throw new PaymentRecordMissingException(String.format("Payment Record Missing with ID %s", id));

        PaymentRecord record = paymentOptional.get();

        PaymentRecord savedRecord = null;

        if (PAYMENT_AUTHENTICATED.equals(record.getPaymentStatus())) {
            String state = paypalInteg.executePayment(record.getId(), record.getPayerId());
            if (TRANSACTION_FAILED.equals(state))
                throw new PaymentExecutionException(String.format("Payment Execution Failed for id %s", id));

            Operation operation = new Operation();
            operation.setId(UUID.randomUUID());
            operation.setType(PAYMENT_EXECUTED);
            record.setPaymentStatus(PAYMENT_EXECUTED);
            record.getTransactions()
                .add(operation);
            savedRecord = recordRepository.save(record);

            Optional<PaymentRequest> requestOptional = requestRepository.findById(record.getIdempotencyKey());
            if (requestOptional.isPresent()) {
                PaymentRequest request = requestOptional.get();
                request.setPaymentStatus(PAYMENT_EXECUTED);
                requestRepository.save(request);
            }
        }

        PaymentEvent event = new PaymentEvent();
        event.setId(id);
        event.setType(PAYMENT_EXECUTED);

        broker.send(topic, event);

        return Optional.ofNullable(savedRecord != null ? savedRecord.getId() : null);
    }

}
