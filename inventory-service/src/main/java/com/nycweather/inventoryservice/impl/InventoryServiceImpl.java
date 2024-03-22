package com.nycweather.inventoryservice.impl;

import com.nycweather.inventoryservice.model.Inventory;
import com.nycweather.inventoryservice.repository.InventoryRepository;
import com.nycweather.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Boolean checkInventory(String productName, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductName(productName);
        return inventory.getQuantity() >= quantity;
    }
}
