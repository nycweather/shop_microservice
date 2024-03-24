package com.nycweather.inventoryservice.service;

import com.nycweather.inventoryservice.dto.AddInventoryRequestDTO;
import com.nycweather.inventoryservice.dto.AddInventoryResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ItemHandlerService {
    ResponseEntity<AddInventoryResponseDTO> addInventory(AddInventoryRequestDTO addInventoryRequestDTO);
}
