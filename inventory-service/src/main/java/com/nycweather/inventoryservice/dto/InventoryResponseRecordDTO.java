package com.nycweather.inventoryservice.dto;

import lombok.Builder;

@Builder
public record InventoryResponseRecordDTO(
        String productId,
        Boolean available
) {
}
