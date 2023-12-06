package com.emailservice.service;

import com.emailservice.model.PaymentMasterInfo;
import com.emailservice.model.PaymentPriceInfo;
import com.emailservice.model.PricingEvent;
import com.emailservice.model.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailSenderService {

    @Value("${cloud.aws.credentials.from-email}")
    private String supportEmail;

    private final MailSender mailSender;

    public MailSenderService(final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendPricingEventEmail(final PricingEvent pricingEvent) {
        mailSender.send(buildSimpleMailMessage(pricingEvent));

        return "Email sent successfully";
    }

    private SimpleMailMessage buildSimpleMailMessage(final PricingEvent pricingEvent) {
        final String quoteName = pricingEvent.getPaymentMaster().getPaymentName();

        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(supportEmail);
        simpleMailMessage.setTo(pricingEvent.getPaymentMaster().getUser().getEmail());
        simpleMailMessage.setSubject("Pricing report for quote '" + quoteName + "' available.");
        simpleMailMessage.setText(buildEmailContent(pricingEvent));

        return simpleMailMessage;
    }

    private String buildEmailContent(final PricingEvent pricingEvent) {
        final PaymentMasterInfo paymentMaster = pricingEvent.getPaymentMaster();
        final UserInfo userInfo = paymentMaster.getUser();
        final List<PaymentPriceInfo> priceInfos = pricingEvent.getPrices();

        final StringBuilder content = new StringBuilder();

        // Build email content using PricingEvent DTO
        content.append("New Pricing Event\n\n");
        content.append("Quote ID: ").append(pricingEvent.getQuoteId()).append("\n");

        // Iterate over PaymentPriceInfo list
        for (PaymentPriceInfo priceInfo : priceInfos) {
            content.append("\nProduct: ").append(priceInfo.getProductName()).append("\n");
            content.append("Old Meter Readings: ").append(priceInfo.getOldMeterReadings()).append("\n");
            content.append("New Meter Readings: ").append(priceInfo.getNewMeterReadings()).append("\n");
            content.append("Price: ").append(priceInfo.getPrice()).append("\n");
        }

        // Append PaymentMasterInfo details
        content.append("\nPayment Master Details:\n");
        content.append("Payment ID: ").append(paymentMaster.getId()).append("\n");
        content.append("Total Price: ").append(paymentMaster.getTotalPrice()).append("\n");
        content.append("User: ")
            .append(userInfo.getFirstName()).append(" ").append(userInfo.getSecondName()).append("\n");
        content.append("Payment Name: ").append(paymentMaster.getPaymentName()).append("\n");

        return content.toString();
    }
}
