package com.nycweather.orderservice.dto;

import lombok.Builder;

@Builder
public record InventoryResponseRecordDTO(
        String productName,
        Boolean available
) {
}
