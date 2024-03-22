package com.nycweather.productservice.exceptions;

public class ProductNameIsEmptyException extends Exception {
    public ProductNameIsEmptyException(String message) {
        super(message);
    }
}
