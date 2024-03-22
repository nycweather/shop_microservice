package com.nycweather.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductRequestDTO(
        @NotBlank(message = "Product name is required")
        String name,

        @NotBlank(message = "Product description is required")
        String description,

        @NotNull(message = "Product price is required")
        @Min(value = 0, message = "Product price must be greater than or equal to 0")
        Double price

) {
}
