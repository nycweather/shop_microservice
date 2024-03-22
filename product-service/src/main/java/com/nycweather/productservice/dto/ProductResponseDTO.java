package com.nycweather.productservice.dto;

import lombok.Builder;

@Builder
public record ProductResponseDTO(
        String id,
        String name,
        String description,
        Double price
) {
}
