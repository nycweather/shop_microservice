package com.nycweather.inventoryservice.impl;

import com.nycweather.inventoryservice.dto.AddInventoryRequestDTO;
import com.nycweather.inventoryservice.dto.AddInventoryResponseDTO;
import com.nycweather.inventoryservice.model.Inventory;
import com.nycweather.inventoryservice.model.InventorySku;
import com.nycweather.inventoryservice.repository.InventoryRepository;
import com.nycweather.inventoryservice.repository.InventorySkuRepository;
import com.nycweather.inventoryservice.service.ItemHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemHandlerServiceImpl implements ItemHandlerService {

    private final InventoryRepository inventoryRepository;
    private final InventorySkuRepository inventorySkuRepository;


    public ItemHandlerServiceImpl(InventoryRepository inventoryRepository, InventorySkuRepository inventorySkuRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventorySkuRepository = inventorySkuRepository;
    }

    public ResponseEntity<AddInventoryResponseDTO> addInventory(AddInventoryRequestDTO addInventoryRequestDTO) {
        inventoryRepository.save(
                Inventory.builder()
                        .productId(addInventoryRequestDTO.productId())
                        .productName(addInventoryRequestDTO.productName())
                        .quantity(addInventoryRequestDTO.quantity())
                        .build()
        );
        List<InventorySku> inventorySkuList = new ArrayList<>();
        for (int i = 0; i < addInventoryRequestDTO.quantity(); i++) {
            inventorySkuList.add(
                    InventorySku.builder()
                            .sku(
                                    addInventoryRequestDTO.productName().replace(" ", "_") + "-" +
                                            addInventoryRequestDTO.productId() + "-" +
                                            addInventoryRequestDTO.sellerId() + "-" +
                                            i
                            )
                            .productId(addInventoryRequestDTO.productId())
                            .productName(addInventoryRequestDTO.productName())
                            .price(addInventoryRequestDTO.price())
                            .sellerId(addInventoryRequestDTO.sellerId())
                            .warehouseId(addInventoryRequestDTO.warehouseId())
                            .build()
            );
        }
        inventorySkuRepository.saveAll(inventorySkuList);
        List<String> skus = inventorySkuList.stream().map(InventorySku::getSku).toList();
        return new ResponseEntity<>(
                AddInventoryResponseDTO.builder()
                        .message("Inventory added successfully")
                        .sellerId(addInventoryRequestDTO.sellerId())
                        .productName(addInventoryRequestDTO.productName())
                        .productId(addInventoryRequestDTO.productId())
                        .price(addInventoryRequestDTO.price())
                        .quantity(addInventoryRequestDTO.quantity())
                        .warehouseId(addInventoryRequestDTO.warehouseId())
                        .skus(skus)
                        .build(),
                HttpStatus.CREATED
        );
    }
}
