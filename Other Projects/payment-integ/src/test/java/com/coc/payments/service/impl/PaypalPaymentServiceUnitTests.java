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

import com.coc.payments.domain.PaymentRecord;
import com.coc.payments.event.PaymentEvent;
import com.coc.payments.exception.PaymentCreationException;
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
    PaymentRecordRepository paymentRepository;

    @Mock
    PaymentRequestRepository requestRepository;

    @Mock
    KafkaTemplate<String, PaymentEvent> paymentBroker;

    @InjectMocks
    PaypalPaymentService paymentService;

    @Test
    public void givenPaymentService_whenCreatePaymentCalled_thenValidRedirectUrl() throws PaymentCreationException {

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
        when(paymentRepository.save(any(PaymentRecord.class))).thenReturn(null);
        when(paymentBroker.send(any(String.class), any(PaymentEvent.class))).thenReturn(null);
        assertEquals(authUrl, paymentService.createPayment(paymentData)
            .get());

    }

}
