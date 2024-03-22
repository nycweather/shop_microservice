package com.nycweather.productservice.dto;

import com.nycweather.productservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDtoMapperTest {
    private ProductDtoMapper productDtoMapper;

    @BeforeEach
    void setUp() {
        productDtoMapper = new ProductDtoMapper();
    }

    @Test
    void ProductDtoMapper_ConvertToProductResponseDTO_GivenProduct() {
        // Given
        Product product = new Product(
                "abcd-1234",
                "Product 1",
                "Item does things",
                10.49);
        // When
        ProductResponseDTO productResponseDTO = productDtoMapper.toProductResponseDTO(product);
        // Then
        assertThat(productResponseDTO.id()).isEqualTo("abcd-1234");
        assertThat(productResponseDTO.name()).isEqualTo("Product 1");
        assertThat(productResponseDTO.description()).isEqualTo("Item does things");
        assertThat(productResponseDTO.price()).isEqualTo(10.49);
    }

    @Test
    void ProductDtoMapper_ConvertToProduct_GivenProductRequest() {
        // Given
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "Product 1",
                "Item does things",
                10.0);
        // When
        Product product = productDtoMapper.toProduct(productRequestDTO);
        // Then
        assertThat(product.getName()).isEqualTo("Product 1");
        assertThat(product.getDescription()).isEqualTo("Item does things");
        assertThat(product.getPrice()).isEqualTo(10.0);
    }
}