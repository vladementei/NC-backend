package com.dementei.ec.inventory.mapper;

import com.dementei.ec.inventory.dto.OrderDto;
import com.dementei.ec.inventory.entity.Order;
import com.dementei.ec.inventory.entity.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderMapper {
    private final ModelMapper mapper;

    @Autowired
    public OrderMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Order toEntity(OrderDto orderDto) {
        Order order = Objects.isNull(orderDto) ? null : mapper.map(orderDto, Order.class);
        for (OrderItem orderItem: order.getOrderItems()){
            orderItem.setOrder(order);
        }
        return order;
    }

    public OrderDto toDto(Order entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, OrderDto.class);
    }
}
