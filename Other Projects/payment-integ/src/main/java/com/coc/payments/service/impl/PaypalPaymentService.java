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

import com.coc.payments.constant.PaymentConstant;
import com.coc.payments.domain.AddressType;
import com.coc.payments.domain.AmountType;
import com.coc.payments.domain.PaymentRecord;
import com.coc.payments.domain.PaymentRequest;
import com.coc.payments.domain.TransactionType;
import com.coc.payments.entity.PaymentData;
import com.coc.payments.event.PaymentEvent;
import com.coc.payments.exception.PaymentCreationException;
import com.coc.payments.exception.PaymentExecutionException;
import com.coc.payments.exception.PaymentRecordMissingException;
import com.coc.payments.integration.PaypalIntegration;
import com.coc.payments.repository.PaymentRecordRepository;
import com.coc.payments.repository.PaymentRequestRepository;
import com.coc.payments.service.PaymentService;

@Service("paypalService")
@PropertySource(value = "classpath:application.yml")
public class PaypalPaymentService implements PaymentService {

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
            if (PaymentConstant.PAYMENT_AUTHENTICATED.equals(request.getPaymentStatus()) || PaymentConstant.PAYMENT_EXECUTED.equals(request.getPaymentStatus()))
                throw new PaymentCreationException(String.format("Payment with the key %s has already been processed.", paymentData.getIdempotencyKey()));
            return Optional.of(requestOptional.get()
                .getAuthUrl());
        }

        PaymentData paymentResponse = paypalInteg.createPayment(paymentData);

        if (PaymentConstant.TRANSACTION_FAILED.equals(paymentResponse.getState()))
            throw new PaymentCreationException(String.format("Payment Creation Failed for id %s", paymentData.getIdempotencyKey()));

        TransactionType transaction = new TransactionType();
        transaction.setId(UUID.randomUUID());
        transaction.setType(PaymentConstant.PAYMENT_CREATED);
        transaction.setUserId(paymentData.getUserId());
        transaction.setAmount(paymentData.getAmount()
            .getTotal());
        AmountType amount = new AmountType();
        amount.setCurrency(paymentData.getAmount()
            .getCurrency());
        amount.setTotal(paymentData.getAmount()
            .getTotal());
        amount.setSubTotal(paymentData.getAmount()
            .getSubTotal());
        amount.setShipping(paymentData.getAmount()
            .getShipping());
        amount.setTax(paymentData.getAmount()
            .getTax());
        AddressType address = new AddressType();
        address.setName(paymentData.getAddress()
            .getFirstName() + " "
            + paymentData.getAddress()
                .getLastName());
        address.setLine1(paymentData.getAddress()
            .getLine1());
        address.setLine2(paymentData.getAddress()
            .getLine2());
        address.setCity(paymentData.getAddress()
            .getCity());
        address.setPostCode(paymentData.getAddress()
            .getPostCode());
        address.setCountryCode(paymentData.getAddress()
            .getCountryCode());
        address.setState(paymentData.getAddress()
            .getState());
        address.setPhone(paymentData.getAddress()
            .getPhone());

        PaymentRecord record = new PaymentRecord();
        record.setId(paymentResponse.getId());
        record.setIdempotencyKey(paymentData.getIdempotencyKey());
        record.setIntent(paymentData.getIntent());
        record.setPaymentStatus(PaymentConstant.PAYMENT_CREATED);
        record.setPaymentProvider(paymentData.getPaymentProvider());
        record.setPaymentMethod(paymentData.getPaymentMethod());
        record.setDescription(paymentData.getDescription());
        record.setUserId(paymentData.getUserId());

        record.getTransactions()
            .add(transaction);
        record.setAmount(amount);
        record.setAddress(address);
        recordRepository.save(record);

        request = new PaymentRequest();
        request.setIdempotencyKey(paymentData.getIdempotencyKey());
        request.setPaymentId(paymentResponse.getId());
        request.setPaymentStatus(record.getPaymentStatus());
        request.setAuthUrl(paymentResponse.getAuthUrl());
        requestRepository.save(request);

        PaymentEvent event = new PaymentEvent();
        event.setId(paymentResponse.getId());
        event.setType(PaymentConstant.PAYMENT_CREATED);
        broker.send(topic, event);

        return Optional.ofNullable(paymentResponse.getAuthUrl());
    }

    @Override
    public Optional<String> authenticatePayment(String token, String paymentId, String payerId) throws PaymentRecordMissingException {

        Optional<PaymentRecord> paymentOptional = recordRepository.findById(paymentId);
        if (!paymentOptional.isPresent())
            throw new PaymentRecordMissingException(String.format("Payment Record Missing with ID %s", paymentId));

        PaymentRecord record = paymentOptional.get();
        PaymentRecord savedRecord = null;

        if (PaymentConstant.PAYMENT_CREATED.equals(record.getPaymentStatus())) {
            TransactionType transaction = new TransactionType();
            transaction.setId(UUID.randomUUID());
            transaction.setType(PaymentConstant.PAYMENT_AUTHENTICATED);
            record.setPayerId(payerId);
            record.setPaymentStatus(PaymentConstant.PAYMENT_AUTHENTICATED);
            record.getTransactions()
                .add(transaction);
            savedRecord = recordRepository.save(record);

            Optional<PaymentRequest> requestOptional = requestRepository.findById(record.getIdempotencyKey());
            if (requestOptional.isPresent()) {
                PaymentRequest request = requestOptional.get();
                request.setPaymentStatus(PaymentConstant.PAYMENT_AUTHENTICATED);
                requestRepository.save(request);
            }
        }

        PaymentEvent event = new PaymentEvent();
        event.setId(paymentId);
        event.setType(PaymentConstant.PAYMENT_AUTHENTICATED);
        broker.send(topic, event);

        return Optional.ofNullable(savedRecord != null ? savedRecord.getId() : null);
    }

    @Override
    public Optional<String> executePayment(String id) throws PaymentRecordMissingException, PaymentExecutionException {

        Optional<PaymentRecord> paymentOptional = recordRepository.findById(id);
        if (!paymentOptional.isPresent())
            throw new PaymentRecordMissingException(String.format("Payment Record Missing with ID %s", id));

        PaymentRecord record = paymentOptional.get();

        PaymentRecord savedRecord = null;

        if (PaymentConstant.PAYMENT_AUTHENTICATED.equals(record.getPaymentStatus())) {
            String state = paypalInteg.executePayment(record.getId(), record.getPayerId());
            if (PaymentConstant.TRANSACTION_FAILED.equals(state))
                throw new PaymentExecutionException(String.format("Payment Execution Failed for id %s", id));

            TransactionType transaction = new TransactionType();
            transaction.setId(UUID.randomUUID());
            transaction.setType(PaymentConstant.PAYMENT_EXECUTED);
            record.setPaymentStatus(PaymentConstant.PAYMENT_EXECUTED);
            record.getTransactions()
                .add(transaction);
            savedRecord = recordRepository.save(record);

            Optional<PaymentRequest> requestOptional = requestRepository.findById(record.getIdempotencyKey());
            if (requestOptional.isPresent()) {
                PaymentRequest request = requestOptional.get();
                request.setPaymentStatus(PaymentConstant.PAYMENT_EXECUTED);
                requestRepository.save(request);
            }
        }

        PaymentEvent event = new PaymentEvent();
        event.setId(id);
        event.setType(PaymentConstant.PAYMENT_EXECUTED);

        broker.send(topic, event);

        return Optional.ofNullable(savedRecord != null ? savedRecord.getId() : null);
    }

    @Override
    public Optional<String> capturePayment(String paymentId, String amount) throws PaymentRecordMissingException {
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<PaymentData> fetchPayment(String paymentId) throws PaymentRecordMissingException {
        return Optional.ofNullable(null);
    }

}
