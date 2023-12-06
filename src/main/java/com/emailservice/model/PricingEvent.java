package com.emailservice.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ToString
public class PricingEvent {

    private Long quoteId;
    private List<PaymentPriceInfo> prices;
    private PaymentMasterInfo paymentMaster;

}
