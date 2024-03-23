package com.nycweather.inventoryservice.dto;

import lombok.Builder;

@Builder
public record InventoryResponseRecordDTO(
        String productName,
        Boolean available
) {
}
