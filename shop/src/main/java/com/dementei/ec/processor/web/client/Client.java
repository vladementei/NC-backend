package com.dementei.ec.processor.web.client;

import com.dementei.ec.processor.dto.CustomerDto;
import com.dementei.ec.processor.dto.OfferDto;
import com.dementei.ec.processor.dto.OrderDto;
import com.dementei.ec.processor.dto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Client {
    private final RestTemplate restTemplate;
    private final String catalogUrl = "http://localhost:8085";
    private final String shopUrl = "http://localhost:8086";

    @Autowired
    public Client(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OfferDto getOfferById(long offerId) {
        ResponseEntity<OfferDto> response = restTemplate.exchange(
                catalogUrl + "/api/v1/offers/getById/" + offerId, HttpMethod.GET, null,
                new ParameterizedTypeReference<OfferDto>() {});
        return response.getBody();
    }

    public CustomerDto getCustomerByEmail(String email) {
        ResponseEntity<CustomerDto> response = restTemplate.exchange(
                shopUrl + "/api/v1/customers/getByEmail/" + email, HttpMethod.GET, null,
                new ParameterizedTypeReference<CustomerDto>() {});
        return response.getBody();
    }

    public List<OrderDto> getAllOrdersByEmail(String email) {
        ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
                shopUrl + "/api/v1/orders/getAll/" + email, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDto>>() {});
        return response.getBody();
    }

    public List<OrderDto> getAllOrdersByPaymentStatus(String paymentStatus) {
        ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
                shopUrl + "/api/v1/orders/getAllByPaymentStatus/" + paymentStatus, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDto>>() {});
        return response.getBody();
    }

    public Double getAllOrdersTotalPriceByEmail(String email) {
        ResponseEntity<Double> response = restTemplate.exchange(
                shopUrl + "/api/v1/orders/getAllTotalPrice/" + email, HttpMethod.GET, null,
                new ParameterizedTypeReference<Double>() {});
        return response.getBody();
    }

    public OrderDto getOrderById(long id) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                shopUrl + "/api/v1/orders/getById/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto addOrderItemToOrder(long orderId, OrderItemDto orderItemDto) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                shopUrl + "/api/v1/orders/addOrderItem/" + orderId, HttpMethod.PUT, new HttpEntity<>(orderItemDto),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto deleteOrderItemFromOrder(long orderId, long orderItemId) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                shopUrl + "/api/v1/orders/deleteOrderItem?id=" + orderId + "&itemId=" + orderItemId, HttpMethod.PUT, null,
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                shopUrl + "/api/v1/orders/saveOrder", HttpMethod.POST, new HttpEntity<>(orderDto),
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }

    public OrderDto payForOrder(long id) {
        ResponseEntity<OrderDto> response = restTemplate.exchange(
                shopUrl + "/api/v1/orders/changePaymentStatus?id=" + id + "&paymentStatus=PAID", HttpMethod.PUT, null,
                new ParameterizedTypeReference<OrderDto>() {});
        return response.getBody();
    }
}
