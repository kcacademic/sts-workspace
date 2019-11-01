package com.coc.payments.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.coc.payments.domain.Operation;
import com.coc.payments.domain.PaymentRecord;
import com.coc.payments.event.PaymentEvent;
import com.coc.payments.repository.PaymentRepository;
import com.coc.payments.service.PaymentService;
import com.coc.payments.vo.PaymentData;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
@PropertySource(value = "classpath:application.yml")
public class PaypalPaymentService implements PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    
    @Value("${payments.kafka.topic}")
    private String topic;

    @Value("${payments.paypal.clientId}")
    private String clientId;

    @Value("${payments.paypal.clientSecret}")
    private String clientSecret;

    @Value("${payments.paypal.successUrl}")
    private String successUrl;

    @Value("${payments.paypal.failureUrl}")
    private String failureUrl;

    @Override
    public String createPayment(PaymentData paymentData) {

        // Create payment details
        Operation operation = new Operation();
        operation.setId(UUID.randomUUID());
        PaymentRecord record = repository.findById(UUID.fromString("aae90430-fbb9-11e9-a936-e176235bcdf6"))
            .get();
        // record.setId(UUID.randomUUID());
        record.getTransactions()
            .add(operation);

        // Persist payment details in Cassandra
        repository.save(record);
        
        // Create payment event
        PaymentEvent event = new PaymentEvent();
        event.setId(UUID.randomUUID());
        
        // Dispatch event to Kafka
        kafkaTemplate.send(topic, event);

        // Set payer details
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        // Set redirect URLs
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(failureUrl);
        redirectUrls.setReturnUrl(successUrl);

        // Set payment details
        Details details = new Details();
        details.setShipping("1");
        details.setSubtotal("5");
        details.setTax("1");

        // Payment amount
        Amount amount = new Amount();
        amount.setCurrency("USD");
        // Total must be equal to sum of shipping, tax and subtotal.
        amount.setTotal("7");
        amount.setDetails(details);

        // Transaction information
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("This is the payment transaction description.");

        // Add transaction to a list
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        // Add payment details
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(transactions);

        // Create payment
        String authUrl = null;
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.create(apiContext);

            Iterator<Links> links = createdPayment.getLinks()
                .iterator();
            while (links.hasNext()) {
                Links link = links.next();
                if (link.getRel()
                    .equalsIgnoreCase("approval_url")) {
                    // Redirect the customer to link.getHref()
                    authUrl = link.getHref();
                    System.out.println(link.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }

        return authUrl;
    }

    @Override
    public PaymentData fetchPayment(String paymentId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String executePayment(String paymentId, String payerId) {

        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment createdPayment = null;
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.execute(apiContext, paymentExecution);
            System.out.println(createdPayment);
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return createdPayment.getState();
    }

    @Override
    public String capturePayment(String paymentId, Float amount) {
        // TODO Auto-generated method stub
        return null;
    }

}
