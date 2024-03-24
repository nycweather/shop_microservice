package com.nycweather.inventoryservice.controller;

import com.nycweather.inventoryservice.dto.AddInventoryRequestDTO;
import com.nycweather.inventoryservice.dto.AddInventoryResponseDTO;
import com.nycweather.inventoryservice.service.InventoryService;
import com.nycweather.inventoryservice.service.ItemHandlerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ItemHandlerService itemHandlerService;

    public InventoryController(InventoryService inventoryService, ItemHandlerService itemHandlerService) {
        this.inventoryService = inventoryService;
        this.itemHandlerService = itemHandlerService;
    }

    @GetMapping("/check")
    public ResponseEntity<Object> checkInventory(@RequestParam List<String> productName, @RequestParam List<Integer> quantity) {
        return inventoryService.checkInventory(productName, quantity);
    }

    @PostMapping("/add_inventory")
    public ResponseEntity<AddInventoryResponseDTO> addInventory(@RequestBody @Valid AddInventoryRequestDTO addInventoryRequestDTO) {
        try {
            log.info("addInventoryRequestDTO = " + addInventoryRequestDTO);
            return itemHandlerService.addInventory(addInventoryRequestDTO);
        } catch (Exception e) {
            log.error("Error occurred while adding inventory", e);
            return new ResponseEntity<>(
                    AddInventoryResponseDTO.builder()
                            .message("Error occurred while adding inventory")
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
