package com.nycweather.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory_sku_table")
public class InventorySku {
    @Id
    private String sku;
    private String productId;
    private String productName;
    private Double price;
    private String sellerId;
    private Integer warehouseId;
}