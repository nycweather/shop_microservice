package com.nycweather.productservice.service;

import com.nycweather.productservice.dto.ProductDtoMapper;
import com.nycweather.productservice.dto.ProductRequestDTO;
import com.nycweather.productservice.dto.ProductResponseDTO;
import com.nycweather.productservice.exceptions.ProductNameIsEmptyException;
import com.nycweather.productservice.impl.ProductServiceImpl;
import com.nycweather.productservice.model.Product;
import com.nycweather.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDtoMapper productDtoMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void ProductServiceTest_createProduct_createAProduct() {
        // Given
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "Product 1",
                "Item does things",
                10.0);

        Product product = Product.builder()
                .name(productRequestDTO.name())
                .description(productRequestDTO.description())
                .price(productRequestDTO.price())
                .build();

        when(productDtoMapper.toProduct(any(ProductRequestDTO.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // When
        productService.createProduct(productRequestDTO);

        // Then
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void ProductServiceTest_getAllProducts_getAllProducts() {
        // Given
        Product product1 = Product.builder()
                .name("Product 1")
                .description("Item does things")
                .price(10.0)
                .build();

        Product product2 = Product.builder()
                .name("Product 2")
                .description("Item does even more things")
                .price(20.0)
                .build();

        List<Product> allProducts = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(allProducts);

        // When
        List<ProductResponseDTO> fetchedProducts = productService.getAllProducts();

        // Then
        assertThat(fetchedProducts).hasSize(2);
        verify(productRepository).findAll();
    }

    @Test
    void ProductServiceTest_findByProductName_findProductByName() throws ProductNameIsEmptyException {
        // Given
        String productName = "Product 1";
        Product product = Product.builder()
                .name(productName)
                .description("Item does things")
                .price(10.0)
                .build();

        List<Product> products = List.of(product);
        when(productRepository.findByProductName(productName)).thenReturn(products);

        // When
        List<ProductResponseDTO> fetchedProducts = productService.findByProductName(productName);

        // Then
        assertThat(fetchedProducts).hasSize(1);
        verify(productRepository).findByProductName(productName);
    }
}