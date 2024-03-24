package com.nycweather.inventoryservice.repository;

import com.nycweather.inventoryservice.model.InventorySku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventorySkuRepository extends JpaRepository<InventorySku, String> {
}
