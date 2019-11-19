package com.dementei.ec.inventory.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private long id = 0;
    private String title = "";
    private String description = "";
    private double price = 0.0;
    private String category = "";
    private long orderId = 0;
}
