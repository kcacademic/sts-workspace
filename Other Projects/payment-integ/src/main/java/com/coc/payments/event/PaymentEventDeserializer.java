package com.coc.payments.event;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentEventDeserializer implements Deserializer<PaymentEvent> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // TODO Auto-generated method stub

    }

    @Override
    public PaymentEvent deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        PaymentEvent event = null;
        try {
            event = mapper.readValue(data, PaymentEvent.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return event;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
