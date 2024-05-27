package com.nycweather.productservice.dto;

import lombok.Builder;

@Builder
public record ProductResponseDTO(
        String productId,
        String name,
        String description,
        Double price
) {
}
