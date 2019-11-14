package com.coc.payments.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coc.payments.constant.PaymentConstant;
import com.coc.payments.domain.PaymentRecord;
import com.coc.payments.event.PaymentEvent;
import com.coc.payments.exception.PaymentCreationException;
import com.coc.payments.exception.PaymentExecutionException;
import com.coc.payments.exception.PaymentRecordMissingException;
import com.coc.payments.integration.PaypalIntegration;
import com.coc.payments.repository.PaymentRecordRepository;
import com.coc.payments.repository.PaymentRequestRepository;
import com.coc.payments.vo.Address;
import com.coc.payments.vo.Amount;
import com.coc.payments.vo.PaymentData;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaypalPaymentServiceUnitTests {

    @Mock
    PaypalIntegration paypalIntegration;

    @Mock
    PaymentRecordRepository recordRepository;

    @Mock
    PaymentRequestRepository requestRepository;

    @Mock
    KafkaTemplate<String, PaymentEvent> paymentBroker;

    @InjectMocks
    PaypalPaymentService paymentService;

    @Test
    public void givenPaymentService_whenCreatePaymentCalled_thenReturnValidRedirectUrl() throws PaymentCreationException {

        String authUrl = "https://paypal.com/auth";
        PaymentData paymentData = new PaymentData();
        paymentData.setIdempotencyKey("12345");
        paymentData.setAuthUrl(authUrl);
        Amount amount = new Amount();
        paymentData.setAmount(amount);
        Address address = new Address();
        paymentData.setAddress(address);
        when(paypalIntegration.createPayment(any(PaymentData.class))).thenReturn(paymentData);
        when(requestRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(null));
        when(recordRepository.save(any(PaymentRecord.class))).thenReturn(null);
        when(paymentBroker.send(any(String.class), any(PaymentEvent.class))).thenReturn(null);
        assertEquals(authUrl, paymentService.createPayment(paymentData)
            .get());

    }

    @Test
    public void givenPaymentService_whenAuthenticatePaymentCalled_thenPaymentAuthenticated() throws PaymentRecordMissingException {

        String token = "dummyToken";
        String paymentId = "dummyPaymentId";
        String payerId = "dummyPayerId";

        PaymentRecord record = new PaymentRecord();
        record.setPaymentStatus(PaymentConstant.PAYMENT_CREATED);
        record.setId(paymentId);

        when(recordRepository.findById(any(String.class))).thenReturn(Optional.of(record));
        when(recordRepository.save(any(PaymentRecord.class))).thenReturn(record);
        when(paymentBroker.send(any(String.class), any(PaymentEvent.class))).thenReturn(null);

        assertEquals(paymentId, paymentService.authenticatePayment(token, paymentId, payerId)
            .get());
    }

    @Test
    public void givenPaymentService_whenExecutePaymentCalled_thenPaymentExecuted() throws PaymentRecordMissingException, PaymentExecutionException {

        String paymentId = "dummyPaymentId";
        String payerId = "dummyPayerId";

        PaymentRecord record = new PaymentRecord();
        record.setPaymentStatus(PaymentConstant.PAYMENT_AUTHENTICATED);
        record.setId(paymentId);
        record.setPayerId(payerId);

        when(paypalIntegration.executePayment(paymentId, payerId)).thenReturn(PaymentConstant.PAYMENT_CREATED);
        when(recordRepository.findById(paymentId)).thenReturn(Optional.of(record));
        when(recordRepository.save(any(PaymentRecord.class))).thenReturn(record);
        when(paymentBroker.send(any(String.class), any(PaymentEvent.class))).thenReturn(null);

        assertEquals(paymentId, paymentService.executePayment(paymentId)
            .get());
    }

    @Test
    public void givenPaymentService_whenFetchPaymentCalled_thenReturnPaymentData() throws PaymentRecordMissingException {

        assertEquals(Optional.ofNullable(null), paymentService.fetchPayment("12345"));

    }

    @Test
    public void givenPaymentService_whenCapturePaymentCalled_thenPaymentCaptured() throws PaymentRecordMissingException {

        assertEquals(Optional.ofNullable(null), paymentService.capturePayment("12345", "1"));

    }

}
