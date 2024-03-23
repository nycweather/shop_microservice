package com.nycweather.orderservice.impl;

import com.nycweather.orderservice.dto.*;
import com.nycweather.orderservice.model.Order;
import com.nycweather.orderservice.model.OrderItem;
import com.nycweather.orderservice.repository.OrderRepository;
import com.nycweather.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;
    private final WebClient webClient;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDtoMapper orderDtoMapper, WebClient webClient) {
        this.orderRepository = orderRepository;
        this.orderDtoMapper = orderDtoMapper;
        this.webClient = webClient;
    }

    public ResponseEntity<Object> createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = Order.builder()
                .orderDate(new Date())
                .orderNumber(1L)
                .customerId(orderRequestDTO.customerId())
                .customerEmail("email.com")
                .build();
        List<OrderItem> orderItems = orderRequestDTO.items().stream()
                .map(item -> OrderItem.builder()
                        .productId(item.productId())
                        .order(order)
                        .price(0.0)
                        .productName("iphone")
                        .quantity(item.quantity())
                        .build())
                .toList();
        order.setOrderItems(orderItems);
        InventoryResponseDTO itemsRequested = webClient.get()
                .uri("http://localhost:8083/api/inventory/check",
                        uriBuilder -> uriBuilder
                                .queryParam("productName", orderItems.stream()
                                        .map(OrderItem::getProductName)
                                        .toList())
                                .queryParam("quantity", orderItems.stream()
                                        .map(OrderItem::getQuantity)
                                        .toList())
                                .build()
                )
                .retrieve()
                .bodyToMono(InventoryResponseDTO.class)
                .block();
        System.out.println(itemsRequested);
        log.info("Inventory check: {}", itemsRequested);

        if (itemsRequested != null && itemsRequested.message().equals("Inventory check successful")) {
            orderRepository.save(order);
            return new ResponseEntity<>(itemsRequested, HttpStatus.CREATED);
        } else {
            log.error("Inventory check failed");
            return new ResponseEntity<>(itemsRequested, HttpStatus.BAD_REQUEST);
        }
    }

    public List<OrderResponseItemDTO> getOrderResponseItemsByOrderId(String orderId) {
        return orderRepository.getOrderItemsByOrderId(orderId).stream()
                .map(orderDtoMapper::orderItemToOrderItemResponseDTO)
                .toList();
    }

    public OrderResponseDTO getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        return orderDtoMapper.toOrderResponseDTO(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderDtoMapper::toOrderResponseDTO)
                .collect(Collectors.toList());

    }

    public List<SingleOrderItemResponseDTO> getOrderInformation(String productSku, String customerEmail) {
        return orderRepository.getOrderInformation(productSku, customerEmail);
    }

}
