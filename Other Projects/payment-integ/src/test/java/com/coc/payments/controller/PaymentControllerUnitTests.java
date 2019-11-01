package com.coc.payments.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.coc.payments.service.PaymentService;
import com.coc.payments.vo.PaymentData;
import com.coc.payments.vo.PaymentRequest;

@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerUnitTests {

    @Mock
    PaymentService paymentService;

    @InjectMocks
    PaymentController paymentController;

    @Test
    public void givenPaymentController_whenCreatePaymentCalled_thenValidRedirectUrl() throws IOException {
        PaymentRequest request = new PaymentRequest();
        String redirectUrl = "";
        when(paymentService.createPayment(any(PaymentData.class))).thenReturn(redirectUrl);
        assertEquals(redirectUrl, paymentController.createPayment(request));
    }

}
