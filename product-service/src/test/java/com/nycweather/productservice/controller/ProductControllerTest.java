package com.nycweather.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nycweather.productservice.dto.ProductRequestDTO;
import com.nycweather.productservice.dto.ProductResponseDTO;
import com.nycweather.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ProductControllerTest_getAllProducts_ReturnsAllProducts() throws Exception {
        // Given
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "Product 1",
                "Item does things",
                10.0
        );

        // When & Then
        productService.createProduct(productRequestDTO);
        List<ProductResponseDTO> productResponseDTOList = productService.getAllProducts();
        given(productService.getAllProducts()).willReturn(productResponseDTOList);
        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void ProductControllerTest_getAllProducts_ErrorOccurs() throws Exception {
        // Given
        doThrow(new RuntimeException("Error occurred")).when(productService).getAllProducts();

        // When & Then
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error occurred while getting all products"));
    }

    @Test
    void ProductControllerTest_searchProducts_ValidProductName() throws Exception {
        // Given
        String productName = "Product 1";
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "Product 1",
                "Item does things",
                10.0
        );
        productService.createProduct(productRequestDTO);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                "1",
                "Product 1",
                "Item does things",
                10.0
        );
        given(productService.findByProductName(productName)).willReturn(Collections.singletonList(productResponseDTO));

        // When & Then
        mockMvc.perform(get("/api/products/search")
                        .param("product", productName))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void ProductControllerTest_searchProducts_ProductNameIsEmpty() throws Exception {
        // Given
        String productName = "";
        String expectedErrorJson = """
                {"statusCode":"400","message":"Product name is empty","timestamp":"%s"}
                """.formatted(String.valueOf(new Date()));

        // When & Then
        mockMvc.perform(get("/api/products/search")
                        .param("product", productName))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedErrorJson, false)); // Note: false indicates ignoring unknown fields and not strictly checking the timestamp
    }

    // Test for createProduct method
    @Test
    void ProductControllerTest_createProduct_SuccessfulCreation() throws Exception {
        // Given
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "Product 1",
                "Item does things",
                10.0
        );

        doNothing().when(productService).createProduct(productRequestDTO);

        // When & Then
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Product is created successfully"));
    }

    @Test
    void ProductControllerTest_createProduct_BadRequest() throws Exception {
        // Given
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                "Product 1",
                "Item does things",
                10.0
        ); // Assume this object is invalid

        // Mock productService to throw an exception when trying to create a product with invalid input
        doThrow(new IllegalArgumentException("Invalid product data")).when(productService).createProduct(any(ProductRequestDTO.class));

        // When & Then
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequestDTO)))
                .andExpect(status().isBadRequest());
        // Optionally check for error message content if your controller advice or error handler adds it to the response
    }
}
