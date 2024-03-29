package com.nycweather.inventoryservice.repository;

import com.nycweather.inventoryservice.model.InventorySku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventorySkuRepository extends JpaRepository<InventorySku, String> {

    @Query(value = "SELECT * FROM inventory_sku_table WHERE product_id = ?1 ORDER BY PRICE ASC LIMIT 1", nativeQuery = true)
    InventorySku findInventorySkuByProductId(String productId);
}

