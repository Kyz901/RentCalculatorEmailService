package com.emailservice.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaymentMasterInfo {

    private Long id;
    private Double totalPrice;
    private UserInfo user;
    private String paymentName;

}
