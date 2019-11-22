package com.dementei.ec.inventory.service;

import com.dementei.ec.inventory.entity.Order;
import com.dementei.ec.inventory.entity.OrderItem;
import com.dementei.ec.inventory.entity.OrderStatus;
import com.dementei.ec.inventory.entity.PaymentStatus;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);

    Order getOrderById(long id);

    List<Order> getAllOrders();

    List<OrderItem> getAllOrderItemsByCategory(String customerEmail, String category);

    Order addOrderItem(long id, OrderItem item);

    Order deleteOrderItem(long id, long itemId);

    Order changeOrderStatus(long id, OrderStatus orderStatus);

    Order changePaymentStatus(long id, PaymentStatus paymentStatus);

    void deleteOrderById(long id);
}
