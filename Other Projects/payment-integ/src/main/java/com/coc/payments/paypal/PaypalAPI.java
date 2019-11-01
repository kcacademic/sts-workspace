package com.coc.payments.paypal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

public class PaypalAPI {
    
    //private static String clientId = "AYSq3RDGsmBLJE-otTkBtM-jBRd1TCQwFf9RGfwddNXWz0uFU9ztymylOhRS";
    //private static String clientSecret = "EGnHDxD_qRPdaLdZz8iCr8N7_MzF-YHPTkjs6NKYQvQSBngp4PTTVWkPZRbL";
    
    private static String clientId = "AR0aSaMTpi29QonY6_8ln80UEiTnr-ykjcd09gPmqkWfqF6KprV37L7trW8bPgGppUkgCgFPbnaIm-pA";
    private static String clientSecret = "ECRsaY2ooG43gbtZGv3or9AAw8BhzbRtYZbeE4DuPa5XT_3bCojdheJRxTNYdn3v96-x1K9PUheIMFvZ";
    
    public static void main(String[] args) {
        
        createPayment();
        
        //executePayment();
        
    }
    
    public static void executePayment() {
        
        String paymentId = "PAYID-LWZKJCQ152684006K815994C";
        String payerId = "MKZ82R85ZKTJW";
        
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
          Payment createdPayment = payment.execute(apiContext, paymentExecution);
          System.out.println(createdPayment);
        } catch (PayPalRESTException e) {
          System.err.println(e.getDetails());
        }
        
    }

    public static void createPayment() {

        // Set payer details
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        // Set redirect URLs
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:3000/cancel");
        redirectUrls.setReturnUrl("http://localhost:3000/process");

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
                    System.out.println(link.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }

    }

}
