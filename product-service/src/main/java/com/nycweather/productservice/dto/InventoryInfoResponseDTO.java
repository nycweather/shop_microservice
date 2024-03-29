package com.nycweather.productservice.dto;

import lombok.Builder;

@Builder
public record InventoryInfoResponseDTO(
        String productName,
        String productId,
        Double price
) {
}
