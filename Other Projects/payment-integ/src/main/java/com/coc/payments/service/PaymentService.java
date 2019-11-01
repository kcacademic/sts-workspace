package com.coc.payments.service;

import com.coc.payments.vo.PaymentData;

public interface PaymentService {
    
    public String createPayment(PaymentData payment);
    
    public PaymentData fetchPayment(String paymentId);
    
    public String executePayment(String paymentId, String payerId);
    
    public String capturePayment(String paymentId, Float amount);

}
