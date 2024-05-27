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
@Table(name = "inventory_table")
public class Inventory {
    @Id
    private String productId;
    private String productName;
    private Integer quantity;
}
