package com.nycweather.orderservice.service;

import com.nycweather.orderservice.dto.OrderRequestDTO;
import com.nycweather.orderservice.dto.OrderResponseDTO;
import com.nycweather.orderservice.dto.SingleOrderItemResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<Object> createOrder(OrderRequestDTO orderRequestDTO);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO getOrder(String orderId);

    List<SingleOrderItemResponseDTO> getOrderInformation(String productSku, String customerEmail);

}
