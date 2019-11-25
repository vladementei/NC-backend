package com.dementei.ec.inventory.dto;

import com.dementei.ec.inventory.entity.OrderStatus;
import com.dementei.ec.inventory.entity.PaymentStatus;
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
    private String paymentStatus = PaymentStatus.NOT_PAID.name();
    private String orderStatus = OrderStatus.IN_PROCESS.name();
}
