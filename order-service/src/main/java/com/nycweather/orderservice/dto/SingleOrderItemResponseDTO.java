package com.nycweather.orderservice.dto;

import lombok.Builder;

import java.util.Date;


@Builder
public record SingleOrderItemResponseDTO(
        String orderId,
        String productName,
        String sku,
        Double price,
        Integer quantity,
        Date orderDate,
        String customerEmail
) {

}
