package com.nycweather.orderservice.dto;

import lombok.Builder;

@Builder
public record OrderResponseItemDTO(
        String productName,
        String sku,
        int quantity,
        double price
) {
}
