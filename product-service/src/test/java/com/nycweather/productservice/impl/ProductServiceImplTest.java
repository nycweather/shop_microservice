package com.nycweather.productservice.impl;

import com.nycweather.productservice.dto.ProductDtoMapper;
import com.nycweather.productservice.dto.ProductRequestDTO;
import com.nycweather.productservice.dto.ProductResponseDTO;
import com.nycweather.productservice.exceptions.ProductNameIsEmptyException;
import com.nycweather.productservice.model.Product;
import com.nycweather.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductDtoMapper productDtoMapper;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository, productDtoMapper);
    }


    @Test
    void ProductServiceImpl_CanGet_AllProducts() {
        // When
        productService.getAllProducts();
        // Then
        verify(productRepository).findAll();
    }

    @Test
    void ProductServiceImpl_CanCreate_SingleProduct() {
        // Given
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "Product 1",
                "Item does things",
                10.0);
        Product product = Product
                .builder()
                .name(productRequestDTO.name())
                .description(productRequestDTO.description())
                .price(productRequestDTO.price())
                .build();

        when(productDtoMapper.toProduct(any(ProductRequestDTO.class))).thenReturn(product);

        // When
        productService.createProduct(productRequestDTO);

        // Then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    void ProductServiceImpl_FindProducts_GivenName() throws ProductNameIsEmptyException {
        // Given
        String productName = "Product 1";
        List<Product> expectedProducts = List.of(new Product());

        when(productRepository.findByProductName(productName)).thenReturn(expectedProducts);

        // When
        List<ProductResponseDTO> actualProducts = productService.findByProductName(productName);

        // Then
        verify(productRepository).findByProductName(productName);
        assertThat(actualProducts).isNotEmpty();
    }
}