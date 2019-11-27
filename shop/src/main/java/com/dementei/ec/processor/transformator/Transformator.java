package com.dementei.ec.processor.transformator;

import com.dementei.ec.processor.dto.OfferDto;
import com.dementei.ec.processor.dto.OrderDto;
import com.dementei.ec.processor.dto.OrderItemDto;
import com.dementei.ec.processor.dto.OrderOffersDto;
import org.springframework.stereotype.Component;

@Component
public class Transformator {
    public OrderItemDto transformOfferToOrderItem(OfferDto offerDto){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setCategory(offerDto.getCategory().getCategory());
        orderItemDto.setTitle(offerDto.getTitle());
        orderItemDto.setDescription(offerDto.getDescription());
        orderItemDto.setPrice(offerDto.getPrice());
        return orderItemDto;
    }

    public OrderDto transformOrderOffersToOrder(OrderOffersDto orderOffersDto){
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail(orderOffersDto.getEmail());
        orderDto.setId(orderOffersDto.getId());
        orderDto.setOrderStatus(orderOffersDto.getOrderStatus());
        orderDto.setPaymentStatus(orderOffersDto.getPaymentStatus());
        orderDto.setTimeStamp(orderOffersDto.getTimeStamp());
        return orderDto;
    }
}
