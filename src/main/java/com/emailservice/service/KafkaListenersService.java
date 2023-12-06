package com.emailservice.service;

import com.emailservice.model.PricingEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenersService {

    private final EmailService emailService;

    public KafkaListenersService(final EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "pricing-events", groupId = "email-group", containerFactory = "pricingEventListener")
    public void handlePricingEvent(final String pricingEventJson) throws JsonProcessingException {
        final PricingEvent pricingEvent = new ObjectMapper().readValue(pricingEventJson, PricingEvent.class);

        // Process the PricingEvent
        System.out.println(pricingEvent.toString());
        emailService.sendEmailNotification(pricingEvent);
    }
}
