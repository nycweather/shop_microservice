package com.nycweather.orderservice.impl;

import com.nycweather.orderservice.dto.*;
import com.nycweather.orderservice.model.Order;
import com.nycweather.orderservice.model.OrderItem;
import com.nycweather.orderservice.repository.OrderRepository;
import com.nycweather.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDtoMapper orderDtoMapper) {
        this.orderRepository = orderRepository;
        this.orderDtoMapper = orderDtoMapper;
    }

    public void createOrder(OrderRequestDTO orderRequestDTO) {
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
                        .productName("product")
                        .quantity(item.quantity())
                        .build())
                .toList();
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        log.info("Order: {} is created successfully", order.getOrderId());
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
