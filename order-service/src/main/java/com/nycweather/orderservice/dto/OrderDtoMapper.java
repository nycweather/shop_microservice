package com.nycweather.orderservice.dto;

import com.nycweather.orderservice.model.Order;
import com.nycweather.orderservice.model.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class OrderDtoMapper {
    public OrderResponseItemDTO orderItemToOrderItemResponseDTO(OrderItem orderItem) {
        return new OrderResponseItemDTO(
                orderItem.getProductName(),
                orderItem.getSku(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }

    public OrderResponseDTO toOrderResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getOrderId(),
                order.getOrderDate().toString(),
                order.getCustomerEmail(),
                order.getOrderItems().stream()
                        .map(this::orderItemToOrderItemResponseDTO)
                        .toList()
        );
    }
}
