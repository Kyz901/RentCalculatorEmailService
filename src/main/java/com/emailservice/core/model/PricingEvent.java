package com.emailservice.core.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class PricingEvent {

    private Long quoteId;
    private Double price;

}
