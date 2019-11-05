package com.coc.payments.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentRequest {
    
    private String idempotencyKey;
    private String userId;
    private String paymentProvider;
    private String paymentMethod;
    private String description;
    private String intent;
    private String subTotal;
    private String shipping;
    private String tax;
    private String total;
    private String currency;

}
