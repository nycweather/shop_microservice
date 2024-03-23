package com.nycweather.inventoryservice.impl;

import com.nycweather.inventoryservice.dto.InventoryResponseDTO;
import com.nycweather.inventoryservice.dto.InventoryResponseRecordDTO;
import com.nycweather.inventoryservice.model.Inventory;
import com.nycweather.inventoryservice.repository.InventoryRepository;
import com.nycweather.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public ResponseEntity<Object> checkInventory(List<String> productName, List<Integer> quantity) {
        if (productName == null || quantity == null) {
            log.error("Product name or quantity is null");
            Map<String, String> response = Map.of("message", "Product name or quantity is null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (productName.size() != quantity.size()) {
            log.error("Product name and quantity size mismatch");
            Map<String, String> response = Map.of("message", "Product name and quantity size mismatch");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Inventory> inventoryList = inventoryRepository.findByProductNameIn(productName);
        if (inventoryList.isEmpty()) {
            log.error("No products found in inventory");
            Map<String, String> response = Map.of("message", "No products found in inventory",
                    "products", productName.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        boolean status = true;
        List<InventoryResponseRecordDTO> returnInventoryList = new ArrayList<>();
        for (Inventory inventoryItem : inventoryList) {
            if (inventoryItem.getQuantity() < quantity.get(inventoryList.indexOf(inventoryItem))) {
                status = false;
            }
            InventoryResponseRecordDTO inventoryResponseRecordDTO = InventoryResponseRecordDTO.builder()
                    .productName(inventoryItem.getProductName())
                    .available(inventoryItem.getQuantity() >= quantity.get(inventoryList.indexOf(inventoryItem)))
                    .build();
            returnInventoryList.add(inventoryResponseRecordDTO);
        }
        String msg = status ? "Inventory check successful" : "Insufficient quantity for some products";
        InventoryResponseDTO response = InventoryResponseDTO.builder()
                .message(msg)
                .items(returnInventoryList)
                .build();
        log.info("Inventory check successful.", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
