package com.nycweather.productservice.impl;

import com.nycweather.productservice.dto.ProductDtoMapper;
import com.nycweather.productservice.dto.ProductRequestDTO;
import com.nycweather.productservice.dto.ProductResponseDTO;
import com.nycweather.productservice.exceptions.ProductNameIsEmptyException;
import com.nycweather.productservice.model.Product;
import com.nycweather.productservice.repository.ProductRepository;
import com.nycweather.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDtoMapper productDtoMapper) {
        this.productRepository = productRepository;
        this.productDtoMapper = productDtoMapper;
    }

    @Override
    public void createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productDtoMapper.toProduct(productRequestDTO);
        productRepository.save(product);
        log.info("Product: {} is created successfully", product.getName());
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        log.info("All products are fetched successfully");
        return productRepository
                .findAll()
                .stream()
                .map(productDtoMapper::toProductResponseDTO)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> findByProductName(String name) throws ProductNameIsEmptyException {
        if (name.isBlank()) {
            log.error("Product name is empty");
            throw new ProductNameIsEmptyException("Product name is empty");
        } else {
            log.info("Products with name: {} are fetched successfully", name);
        }
        return productRepository
                .findByProductName(name)
                .stream()
                .map(productDtoMapper::toProductResponseDTO)
                .toList();
    }
}
