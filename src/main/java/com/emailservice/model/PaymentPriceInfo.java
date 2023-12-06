package com.emailservice.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaymentPriceInfo {

    private Integer oldMeterReadings;
    private Integer newMeterReadings;
    private String productName;
    private Double price;

}
