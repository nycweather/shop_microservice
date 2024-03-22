package com.nycweather.inventoryservice.repository;

import com.nycweather.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Inventory findByProductName(String productName);
}
