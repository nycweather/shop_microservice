package com.nycweather.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory_table")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String productName;
    private Integer quantity;
}
