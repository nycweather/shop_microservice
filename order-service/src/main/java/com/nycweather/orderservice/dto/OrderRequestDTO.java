package com.nycweather.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequestDTO(
        @NotBlank(message = "Customer ID is required")
        String customerId,
        List<OrderRequestItemDTO> items
) {
}
