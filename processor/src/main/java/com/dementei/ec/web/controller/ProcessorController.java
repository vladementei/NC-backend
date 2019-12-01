package com.dementei.ec.web.controller;

import com.dementei.ec.dto.*;
import com.dementei.ec.transformator.Transformator;
import com.dementei.ec.web.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/processor")
public class ProcessorController {
    private final Client client;
    private final Transformator transformator;

    @Autowired
    public ProcessorController(Client client, Transformator transformator) {
        this.client = client;
        this.transformator = transformator;
    }

    @PostMapping("/order")
    public OrderDto saveOrder(@RequestBody OrderOffersDto orderOffersDto) {
        CustomerDto customerDto = client.getCustomerByEmail(orderOffersDto.getEmail());
        OrderDto orderDto = transformator.transformOrderOffersToOrder(orderOffersDto);
        orderDto.setEmail(customerDto.getEmail());
        Set<OfferDto> offerDtoSet = orderOffersDto.getOffers().stream().map(client::getOfferById).collect(Collectors.toSet());
        orderDto.setOrderItems(offerDtoSet.stream().map(transformator::transformOfferToOrderItem).collect(Collectors.toSet()));
        return client.saveOrder(orderDto);
    }

    @PutMapping(value = "/order/{id}/add", params = {"offerId"})
    public OrderDto addOfferToOrder(@PathVariable("id") long orderId, @RequestParam("offerId") long offerId) {
        OfferDto offerDto = client.getOfferById(offerId);
        OrderItemDto orderItemDto = transformator.transformOfferToOrderItem(offerDto);
        return client.addOrderItemToOrder(orderId, orderItemDto);
    }

    @PutMapping(value = "/order/{id}/delete", params = "orderItemId")
    public OrderDto deleteOrderItemFromOrder(@PathVariable("id") long orderId, @RequestParam("orderItemId") long orderItemId) {
        return client.deleteOrderItemFromOrder(orderId, orderItemId);
    }

    @GetMapping("/{email}/total-sum")
    public double getAllOrdersTotalPriceByEmail(@PathVariable("email") String email) {
        return client.getAllOrdersTotalPriceByEmail(email);
    }

    @GetMapping("/{email}/amount")
    public int getOrdersAmountByEmail(@PathVariable("email") String email) {
        return client.getAllOrdersByEmail(email).size();
    }

    @GetMapping("/{email}/orders")
    public List<OrderDto> getAllOrdersByEmail(@PathVariable("email") String email) {
        return client.getAllOrdersByEmail(email);
    }

    @GetMapping("/orders/payment/{paymentStatus}")
    public List<OrderDto> getAllOrdersByPaymentStatus(@PathVariable("paymentStatus") String paymentStatus) {
        return client.getAllOrdersByPaymentStatus(paymentStatus);
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrderById(@PathVariable("id") long id) {
        return client.getOrderById(id);
    }

    @PutMapping("/orders/{id}/pay")
    public OrderDto payForOrderById(@PathVariable("id") long id) {
        return client.payForOrder(id);
    }
}
