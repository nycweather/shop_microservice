package com.nycweather.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddInventoryRequestDTO(
        @NotBlank(message = "Seller ID is required")
        String sellerId,
        @NotBlank(message = "Product name is required")
        String productName,
        @NotBlank(message = "Product ID is required")
        String productId,
        @NotNull(message = "Product price is required")
        @Min(value = 0, message = "Product price must be greater than or equal to 0")
        Double price,
        @NotNull(message = "Product quantity is required")
        @Min(value = 1, message = "Product quantity must be greater than or equal to 1")
        Integer quantity,
        @NotNull(message = "Warehouse ID is required")
        @Min(value = 1, message = "Warehouse ID must be greater than or equal to 0")
        Integer warehouseId
) {
}
