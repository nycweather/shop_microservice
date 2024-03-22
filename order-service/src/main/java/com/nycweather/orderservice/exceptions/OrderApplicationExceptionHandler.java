package com.nycweather.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class OrderApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorObject error = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                "Order Validation Failed",
                new Date(),
                exception);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
