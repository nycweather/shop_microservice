package com.nycweather.productservice.service;

import com.nycweather.productservice.dto.ProductRequestDTO;
import com.nycweather.productservice.dto.ProductResponseDTO;
import com.nycweather.productservice.exceptions.ProductNameIsEmptyException;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAllProducts();

    List<ProductResponseDTO> findByProductName(String name) throws ProductNameIsEmptyException;
}
