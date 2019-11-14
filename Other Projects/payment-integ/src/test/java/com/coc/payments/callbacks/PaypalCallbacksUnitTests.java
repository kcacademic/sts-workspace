package com.coc.payments.callbacks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coc.payments.callback.PaypalCallbacks;
import com.coc.payments.exception.PaymentRecordMissingException;
import com.coc.payments.service.PaymentService;
import com.coc.payments.to.PaymentAddress;
import com.coc.payments.to.PaymentAmount;
import com.coc.payments.to.PaymentRequest;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaypalCallbacksUnitTests {

    @Mock
    PaymentService paymentService;

    @InjectMocks
    PaypalCallbacks paypalCallbacks;

    @Test
    public void givenPaypalCallbacks_whenAuthSuccessCalled_thenPaymentSaved() throws PaymentRecordMissingException {

        String token = "dummyToken";
        String paymentId = "dummyPaymentId";
        String payerId = "dummyPayerId";

        PaymentRequest request = new PaymentRequest();
        PaymentAmount amount = new PaymentAmount();
        request.setAmount(amount);
        PaymentAddress address = new PaymentAddress();
        request.setAddress(address);
        when(paymentService.authenticatePayment(token, paymentId, payerId)).thenReturn(Optional.of(paymentId));
        assertEquals(paymentId, paypalCallbacks.authSuccess(token, paymentId, payerId)
            .getBody());
    }

    @Test
    public void givenPaypalCallbacks_whenAuthFailureCalled_thenPaymentSaved() {

        String token = "dummyToken";
        assertEquals(HttpStatus.BAD_REQUEST, paypalCallbacks.authFailure(token)
            .getStatusCode());

    }

}
