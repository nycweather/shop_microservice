package com.nycweather.inventoryservice.repository;

import com.nycweather.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findByProductNameIn(List<String> productName);
}
