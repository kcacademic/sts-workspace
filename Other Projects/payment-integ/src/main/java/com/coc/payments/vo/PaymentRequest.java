package com.coc.payments.vo;

import io.swagger.annotations.ApiModelProperty;
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
    
    @ApiModelProperty(notes = "The idempotency key for the payment request.", required = true)
    private String idempotencyKey;
    @ApiModelProperty(notes = "The user ID initiating the payment creation.", required = false)
    private String userId;
    @ApiModelProperty(notes = "The payment provider to create payment (paypal/cybersource).", required = true)
    private String paymentProvider;
    @ApiModelProperty(notes = "The payment method to use to create payment,(paypal, credit card).", required = true)
    private String paymentMethod;
    @ApiModelProperty(notes = "The description for the payment.", required = false)
    private String description;
    @ApiModelProperty(notes = "The intent to create payment with, (sale/auth).", required = true)
    private String intent;
    @ApiModelProperty(notes = "The sub total value of the payment to be created.", required = true)
    private String subTotal;
    @ApiModelProperty(notes = "The shipping value of the payment to be created.", required = true)
    private String shipping;
    @ApiModelProperty(notes = "The tax value of the payment to be created.", required = true)
    private String tax;
    @ApiModelProperty(notes = "The total value of the payment to be created.", required = true)
    private String total;
    @ApiModelProperty(notes = "The currency of the payment to be created.", required = true)
    private String currency;

}
