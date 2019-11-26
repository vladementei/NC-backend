package com.dementei.ec.inventory.web.controller;

import com.dementei.ec.inventory.dto.OrderDto;
import com.dementei.ec.inventory.dto.OrderItemDto;
import com.dementei.ec.inventory.entity.Order;
import com.dementei.ec.inventory.entity.OrderItem;
import com.dementei.ec.inventory.entity.OrderStatus;
import com.dementei.ec.inventory.entity.PaymentStatus;
import com.dementei.ec.inventory.mapper.OrderItemMapper;
import com.dementei.ec.inventory.mapper.OrderMapper;
import com.dementei.ec.inventory.service.DefaultOrderService;
import com.dementei.ec.inventory.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderController(DefaultOrderService orderService, OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order savedOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(orderMapper.toDto(savedOrder), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderDto> findOrder(@PathVariable("id") long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> findAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDto> orderDtoList = orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAll/{email}")
    public ResponseEntity<List<OrderDto>> findAllOrdersByEmail(@PathVariable("email") String email) {
        List<Order> orders = orderService.getAllOrders(email);
        List<OrderDto> orderDtoList = orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAllTotalPrice/{email}")
    public ResponseEntity<Double> getAllOrdersTotalPriceByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(orderService.getAllOrdersTotalPriceByEmail(email), HttpStatus.OK);
    }

    @GetMapping(value = "/findByCategory", params = {"customerEmail", "category"})
    public ResponseEntity<List<OrderItemDto>> findOrderItemsByCategory(@RequestParam("customerEmail") String customerEmail, @RequestParam("category") String category) {
        List<OrderItem> orderItemList = orderService.getAllOrderItemsByCategory(customerEmail, category);
        List<OrderItemDto> orderItemDtoList = orderItemList.stream().map(orderItemMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderItemDtoList, HttpStatus.OK);
    }

    @PutMapping("/addOrderItem/{id}")
    public ResponseEntity<OrderDto> addOrderItem(@PathVariable("id") long id, @RequestBody OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemMapper.toEntity(orderItemDto);
        Order updatedOrder = orderService.addOrderItem(id, orderItem);
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @PutMapping(value = "/deleteOrderItem", params = {"id", "itemId"})
    public ResponseEntity<OrderDto> deleteOrderItem(@RequestParam("id") long id, @RequestParam("itemId") long itemId) {
        Order updatedOrder = orderService.deleteOrderItem(id, itemId);
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @PutMapping(value = "/changeOrderStatus", params = {"id", "orderStatus"})
    public ResponseEntity<OrderDto> changeOrderStatus(@RequestParam("id") long id, @RequestParam("orderStatus") String orderStatus) {
        Order updatedOrder = orderService.changeOrderStatus(id, OrderStatus.valueOf(orderStatus));
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @PutMapping(value = "/changePaymentStatus", params = {"id", "paymentStatus"})
    public ResponseEntity<OrderDto> changePaymentStatus(@RequestParam("id") long id, @RequestParam("paymentStatus") String paymentStatus) {
        Order updatedOrder = orderService.changePaymentStatus(id, PaymentStatus.valueOf(paymentStatus));
        return new ResponseEntity<>(orderMapper.toDto(updatedOrder), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
