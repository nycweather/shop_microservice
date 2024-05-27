package com.nycweather.productservice.impl;

import com.nycweather.productservice.config.WebClientConfig;
import com.nycweather.productservice.dto.InventoryInfoResponseDTO;
import com.nycweather.productservice.dto.ProductRequestDTO;
import com.nycweather.productservice.model.Product;
import com.nycweather.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductAsyncTasks {

    private final WebClientConfig webClientConfig;
    private final ProductRepository productRepository;

    public ProductAsyncTasks(WebClientConfig webClientConfig, ProductRepository productRepository) {
        this.webClientConfig = webClientConfig;
        this.productRepository = productRepository;
    }

    @Async("asyncTaskExecutor")
    public void addProductInformation(ProductRequestDTO productRequestDTO) {
        log.info("Adding product information for product id: {}", productRequestDTO.productId());
        InventoryInfoResponseDTO item = webClientConfig.webClientBuilder()
                .build()
                .get()
                .uri("http://inventory-service/api/inventory/info_dump/" + productRequestDTO.productId())
                .retrieve()
                .bodyToMono(InventoryInfoResponseDTO.class)
                .block();
        assert item != null;
        Product product = Product.builder()
                .productId(productRequestDTO.productId())
                .name(item.productName())
                .description(productRequestDTO.description())
                .price(item.price())
                .build();
        productRepository.save(product);
    }
}
