package com.coc.payments.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coc.payments.exception.PaymentCreationException;
import com.coc.payments.service.PaymentService;
import com.coc.payments.to.PaymentRequest;
import com.coc.payments.to.PaymentResponse;
import com.coc.payments.vo.Address;
import com.coc.payments.vo.Amount;
import com.coc.payments.vo.PaymentData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Payments API")
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    PaymentService service;

    @PostMapping("/payments/paypal")
    @ApiOperation(value = "Paypal Payment Creation Operation", response = String.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Payment with Paypal could not be processed at this moment.") })
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) {
        PaymentData payment = new PaymentData();
        payment.setIdempotencyKey(request.getIdempotencyKey());
        payment.setUserId(request.getUserId());
        payment.setPaymentProvider(request.getPaymentProvider());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setIntent(request.getIntent());
        payment.setDescription(request.getDescription());

        Amount amount = new Amount();
        amount.setCurrency(request.getAmount()
            .getCurrency());
        amount.setTotal(request.getAmount()
            .getTotal());
        amount.setSubTotal(request.getAmount()
            .getSubTotal());
        amount.setShipping(request.getAmount()
            .getShipping());
        amount.setTax(request.getAmount()
            .getTax());
        payment.setAmount(amount);

        Address address = new Address();
        address.setName(request.getAddress()
            .getName());
        address.setLine1(request.getAddress()
            .getLine1());
        address.setLine2(request.getAddress()
            .getLine2());
        address.setCity(request.getAddress()
            .getCity());
        address.setPostCode(request.getAddress()
            .getPostCode());
        address.setCountryCode(request.getAddress()
            .getCountryCode());
        address.setState(request.getAddress()
            .getState());
        address.setPhone(request.getAddress()
            .getPhone());
        payment.setAddress(address);

        PaymentResponse response = new PaymentResponse();
        try {
            Optional<String> optional = service.createPayment(payment);
            if (optional.isPresent()) {
                response.setStatus(HttpStatus.OK.getReasonPhrase());
                response.setMessage("Payment with Paypal successfully created, please proceed with authentication.");
                response.setAuthUrl(optional.get());
            }
            else {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                response.setMessage("Payment with Paypal could not be processed at this moment.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
            }
        } catch (PaymentCreationException e) {
            logger.error(e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
        }
        return ResponseEntity.ok(response);
    }

}
