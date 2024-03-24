package com.nycweather.inventoryservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class InventoryApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorObject error = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                "Product Validation Failed",
                new Date(),
                exception);
        log.error("Validation error occurred", exception);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ProductNameIsEmptyException.class)
//    public ResponseEntity<Map<String, String>> handleProductNameIsEmptyException(ProductNameIsEmptyException exception) {
//        Map<String, String> error = new LinkedHashMap<>();
//        error.put("statusCode", String.valueOf(HttpStatus.BAD_REQUEST.value()));
//        error.put("message", exception.getMessage());
//        error.put("timestamp", String.valueOf(new Date()));
//        log.error("Product name is empty", exception);
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }

}
