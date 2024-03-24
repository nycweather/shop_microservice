package com.nycweather.inventoryservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AddInventoryResponseDTO(
        String message,
        String sellerId,
        String productName,
        String productId,
        Double price,
        Integer quantity,
        Integer warehouseId,
        List<String> skus
) {
}
