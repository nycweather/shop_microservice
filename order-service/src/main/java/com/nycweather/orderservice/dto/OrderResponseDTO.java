package com.nycweather.orderservice.dto;

import java.util.List;

public record OrderResponseDTO(
        String orderId,
        String orderDate,
        String customerEmail,
        List<OrderResponseItemDTO> items
) {
}
