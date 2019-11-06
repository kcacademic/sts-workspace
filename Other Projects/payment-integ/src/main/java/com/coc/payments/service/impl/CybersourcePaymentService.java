package com.coc.payments.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cybersource.ws.client.Client;
import com.cybersource.ws.client.ClientException;
import com.cybersource.ws.client.FaultException;

public class CybersourcePaymentService {

    static Logger logger = LoggerFactory.getLogger(CybersourcePaymentService.class);

    public static void main(String[] args) {

        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/cybs.properties");
            props.load(fis);
            fis.close();
        } catch (IOException ioe) {
            logger.error("File not found");
        }

        HashMap<String, String> request = new HashMap<>();
        // In this sample, we are processing a credit card authorization.
        request.put("ccAuthService_run", "true");
        // Add required fields
        request.put("merchantReferenceCode", "coc_cybersource");
        request.put("billTo_firstName", "Kumar");
        request.put("billTo_lastName", "Chandrakant");
        request.put("billTo_street1", "Hudson St.");
        request.put("billTo_city", "Mountain View");
        request.put("billTo_state", "NY");
        request.put("billTo_postalCode", "10014");
        request.put("billTo_country", "US");
        request.put("billTo_email", "kchandrakant@sapient.com");
        request.put("card_accountNumber", "4111111111111111");
        request.put("card_expirationMonth", "12");
        request.put("card_expirationYear", "2010");
        request.put("purchaseTotals_currency", "USD");
        // This sample order contains two line items.
        request.put("item_0_unitPrice", "12.34");
        request.put("item_1_unitPrice", "56.78");
        // Add optional fields here according to your business needs.
        // For information about processing the reply,
        // see Using the Decision and Reason Code Fields.

        try {
            @SuppressWarnings({ "rawtypes" })
            Map reply = Client.runTransaction(request, props);
            if (logger.isDebugEnabled())
                logger.debug(reply.toString());
        } catch (ClientException | FaultException e) {
            logger.error(e.getMessage());
        }
    }
}