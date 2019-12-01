package com.dementei.ec.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderOffersDto {
    private long id = 0;
    private Set<Long> offers = new HashSet<>();
    private String email = "";
    private Calendar timeStamp = Calendar.getInstance();
    private String paymentStatus = PaymentStatus.NOT_PAID.name();
    private String orderStatus = OrderStatus.IN_PROCESS.name();
}
