package com.nycweather.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record OrderRequestItemDTO(
        @NotBlank(message = "Product ID is required")
        String productId,
        @NotBlank(message = "Product quantity is required")
        Integer quantity
) {

}
