package com.nycweather.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ProductRequestDTO(
        @NotBlank(message = "Product ID is required")
        String productId,

        @NotBlank(message = "Product description is required")
        String description

) {
}
