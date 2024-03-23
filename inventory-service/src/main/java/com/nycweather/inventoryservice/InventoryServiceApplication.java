package com.nycweather.inventoryservice;

import com.nycweather.inventoryservice.model.Inventory;
import com.nycweather.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            inventoryRepository.save(Inventory.builder()
                    .productName("iphone")
                    .quantity(1)
                    .build());
            inventoryRepository.save(Inventory.builder()
                    .productName("iphone12")
                    .quantity(2)
                    .build());
        };
    }


}
