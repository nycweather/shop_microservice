package com.nycweather.inventoryservice.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {
    ResponseEntity<Object> checkInventory(List<String> productId, List<Integer> quantity);
}
