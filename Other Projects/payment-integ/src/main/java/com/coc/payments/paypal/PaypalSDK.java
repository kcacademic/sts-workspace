package com.coc.payments.paypal;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.openidconnect.Session;
import com.paypal.api.openidconnect.Tokeninfo;
import com.paypal.api.openidconnect.Userinfo;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaypalSDK {
    String clientId = "AYSq3RDGsmBLJE-otTkBtM-jBRd1TCQwFf9RGfwddNXWz0uFU9ztymylOhRS";
    String clientSecret = "EGnHDxD_qRPdaLdZz8iCr8N7_MzF-YHPTkjs6NKYQvQSBngp4PTTVWkPZRbL";

    public static void main(String args[]) {
        PaypalSDK integration = new PaypalSDK();
        integration.capturePayPalAPI();
    }

    public void capturePayPalAPI() {

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal("1.00");

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://example.com/cancel");
        redirectUrls.setReturnUrl("https://example.com/return");
        payment.setRedirectUrls(redirectUrls);

        try {
            // Initialize apiContext with proper credentials and environment.
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

            @SuppressWarnings("serial")
            List<String> scopes = new ArrayList<String>() {
                {
                    add("openid");
                    add("profile");
                    add("email");
                }
            };
            String redirectUrl = Session.getRedirectURL("https://example.com/return", scopes, apiContext);
            System.out.println("Redirect URL: " + redirectUrl);

            // Replace the code with the code value returned from the redirect on previous step.
            String code = "";
            Tokeninfo info = Tokeninfo.createFromAuthorizationCode(apiContext, code);
            String accessToken = info.getAccessToken();
            String refreshToken = info.getRefreshToken();
            System.out.println("Access Token: " + accessToken + " Refresh Token: " + refreshToken);
            
            Userinfo userinfo = Userinfo.getUserinfo(apiContext);
            System.out.println("User Info: " + userinfo);

            Payment createdPayment = payment.create(apiContext);
            System.out.println(createdPayment.toString());
        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}