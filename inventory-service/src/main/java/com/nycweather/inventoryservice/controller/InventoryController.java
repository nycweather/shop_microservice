package com.nycweather.inventoryservice.controller;

import com.nycweather.inventoryservice.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    public final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkInventory(@RequestParam String productName, @RequestParam Integer quantity) {
        if (inventoryService.checkInventory(productName, quantity)) {
            return ResponseEntity.ok("Inventory is available");
        } else {
            return ResponseEntity.ok("Inventory is not available");
        }
    }
}
