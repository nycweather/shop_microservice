package com.nycweather.inventoryservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record InventoryResponseDTO(
        String message,
        List<InventoryResponseRecordDTO> items
) {
}
