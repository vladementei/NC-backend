package com.dementei.ec.inventory.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDto {
    private long id = 0;
    private Set<OrderItemDto> orderItems = new HashSet<>();
    private String email = "";
    private Calendar timeStamp = Calendar.getInstance();
    private String paymentStatus = "";
    private String orderStatus = "";
}
