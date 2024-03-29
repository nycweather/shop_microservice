package com.nycweather.productservice.dto;

import com.nycweather.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public Product toProduct(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .productId(productRequestDTO.productId())
                .description(productRequestDTO.description())
                .build();
    }
}
