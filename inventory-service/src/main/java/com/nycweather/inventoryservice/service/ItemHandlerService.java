package com.nycweather.inventoryservice.service;

import com.nycweather.inventoryservice.dto.AddInventoryRequestDTO;
import com.nycweather.inventoryservice.dto.AddInventoryResponseDTO;
import com.nycweather.inventoryservice.dto.InventoryInfoResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ItemHandlerService {
    ResponseEntity<AddInventoryResponseDTO> addInventory(AddInventoryRequestDTO addInventoryRequestDTO);

    ResponseEntity<InventoryInfoResponseDTO> getInventoryInfo(String productId);
}
