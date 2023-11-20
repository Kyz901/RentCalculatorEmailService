package com.emailservice.core.service;

import com.emailservice.core.model.PricingEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @KafkaListener(topics = "pricing-events", groupId = "email-group")
    public void handlePricingEvent(final String pricingEventJson) throws JsonProcessingException {
        // Trigger email notification process
        System.out.println(pricingEventJson);
        PricingEvent obj = new ObjectMapper().readValue(pricingEventJson, PricingEvent.class);
        // Process the PricingEvent
        System.out.println(obj.toString());
        sendEmailNotification(obj);
    }

    private void sendEmailNotification(PricingEvent pricingEvent) {
        System.out.println("Pricing completed for quote id: " + pricingEvent.getQuoteId());
    }
}
