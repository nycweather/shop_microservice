package com.nycweather.inventoryservice.service;

public interface InventoryService {
    Boolean checkInventory(String productName, Integer quantity);
}
