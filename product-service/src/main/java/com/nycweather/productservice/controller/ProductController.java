package com.nycweather.productservice.controller;

import com.nycweather.productservice.dto.ProductRequestDTO;
import com.nycweather.productservice.exceptions.ProductNameIsEmptyException;
import com.nycweather.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllProducts() {
        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting all products", e);
            return new ResponseEntity<>("Error occurred while getting all products", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchProducts(@RequestParam(name = "product") String productName)
            throws ProductNameIsEmptyException {
        if (productName.isBlank()) {
            throw new ProductNameIsEmptyException("Product name is empty");
        }
        try {
            return new ResponseEntity<>(productService.findByProductName(productName), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while searching products", e);
            return new ResponseEntity<>("Error occurred while searching products", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        try {
            productService.createProduct(productRequestDTO);
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while creating product", e);
            return new ResponseEntity<>("Error occurred while creating product", HttpStatus.BAD_REQUEST);
        }
    }

}
