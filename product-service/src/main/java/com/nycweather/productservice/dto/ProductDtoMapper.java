package com.nycweather.productservice.dto;

import com.nycweather.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public Product toProduct(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .name(productRequestDTO.name())
                .description(productRequestDTO.description())
                .price(productRequestDTO.price())
                .build();
    }
}
