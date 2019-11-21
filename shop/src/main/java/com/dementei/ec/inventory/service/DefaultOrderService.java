package com.dementei.ec.inventory.service;

import com.dementei.ec.customer.exception.NotFoundException;
import com.dementei.ec.inventory.entity.Order;
import com.dementei.ec.inventory.entity.OrderItem;
import com.dementei.ec.inventory.entity.OrderStatus;
import com.dementei.ec.inventory.entity.PaymentStatus;
import com.dementei.ec.inventory.repository.OrderItemRepository;
import com.dementei.ec.inventory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        Optional<Order> foundedOrder = this.orderRepository.findById(order.getId());
        return foundedOrder.orElseGet(() -> this.orderRepository.save(order));
    }

    @Override
    public Order getOrderById(long id) {
        Optional<Order> foundedOrder = this.orderRepository.findById(id);
        if(foundedOrder.isPresent()){
            return foundedOrder.get();
        } else {
            throw new NotFoundException("No order with id " + id);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<OrderItem> getAllOrderItemsByCategory(String customerEmail, String category) {
        List<Order> customerOrders = this.orderRepository.findAllByEmail(customerEmail);
        List<OrderItem> orderItems = new ArrayList<>();
        customerOrders.forEach(order -> order.getOrderItems().forEach(orderItem -> {
                if (orderItem.getCategory().equals(category)) {
                    orderItems.add(orderItem);
                }})
        );
        return orderItems;
    }

    @Override
    public Order addOrderItem(long id, OrderItem item) {
        Order foundedOrder = getOrderById(id);
        item.setOrder(foundedOrder);
        foundedOrder.getOrderItems().add(item);
        return this.orderRepository.save(foundedOrder);
    }

    @Override
    public Order deleteOrderItem(long id, long itemId) {
        Order foundedOrder = getOrderById(id);
        for(OrderItem orderItem: foundedOrder.getOrderItems()){
            if(orderItem.getId() == itemId){
                foundedOrder.getOrderItems().remove(orderItem);
                this.orderItemRepository.deleteById(itemId);
                break;
            }
        }
        return foundedOrder;
    }

    @Override
    public Order changeOrderStatus(long id, OrderStatus orderStatus) {
        Order foundedOrder = getOrderById(id);
        foundedOrder.setOrderStatus(orderStatus);
        return this.orderRepository.save(foundedOrder);
    }

    @Override
    public Order changePaymentStatus(long id, PaymentStatus paymentStatus) {
        Order foundedOrder = getOrderById(id);
        foundedOrder.setPaymentStatus(paymentStatus);
        return this.orderRepository.save(foundedOrder);
    }

    @Override
    public void deleteOrderById(long id) {
        this.orderRepository.deleteById(id);
    }
}
