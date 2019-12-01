package com.dementei.ec.web.client;

import com.dementei.ec.dto.OrderDto;
import com.dementei.ec.dto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrderClient {
    private final RestTemplate restTemplate;
    private final String ordersUrl = "http://localhost:8086/api/v1/orders/";

    @Autowired
    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrderDto> getAllOrdersByEmail(String email) {
        ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
                ordersUrl + "email/" + email, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDto>>() {});
        return response.getBody();
    }

    public List<OrderDto> getAllOrdersByPaymentStatus(String paymentStatus) {
        ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
                ordersUrl + "payment/" + paymentStatus, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDto>>() {});
        return response.getBody();
    }

    public Double getAllOrdersTotalPriceByEmail(String email) {
        ResponseEntity<Double> response = restTemplate.exchange(
                ordersUrl + email + "/total-price", HttpMethod.GET, null,
                new ParameterizedTypeReference<Double>() {});
        return response.getBody();
    }

    public OrderDto getOrderById(long id) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto addOrderItemToOrder(long orderId, OrderItemDto orderItemDto) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + orderId + "/add", HttpMethod.PUT, new HttpEntity<>(orderItemDto),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto deleteOrderItemFromOrder(long orderId, long orderItemId) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + orderId + "/delete?itemId=" + orderItemId, HttpMethod.PUT, null,
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl, HttpMethod.POST, new HttpEntity<>(orderDto),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto payForOrder(long id) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                ordersUrl + id + "?paymentStatus=PAID", HttpMethod.PUT, null,
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }
}
