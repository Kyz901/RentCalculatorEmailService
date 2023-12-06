package com.emailservice.service;

import com.emailservice.model.PricingEvent;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final MailSenderService mailService;

    public EmailService(
        final MailSenderService mailService
    ) {
        this.mailService = mailService;
    }
    public void sendEmailNotification(final PricingEvent pricingEvent) {
        System.out.println("Pricing completed for quote id: " + pricingEvent.getQuoteId());
        System.out.println("Sending email for quote id: " + pricingEvent.getQuoteId());

        final String result = mailService.sendPricingEventEmail(pricingEvent);
        System.out.println("Completed:" + result);
    }

}
