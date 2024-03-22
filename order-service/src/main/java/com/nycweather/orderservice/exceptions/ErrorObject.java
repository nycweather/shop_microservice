package com.nycweather.orderservice.exceptions;

import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorObject {
    private final Integer statusCode;
    private final String message;
    private final Date timestamp;
    private Map<String, String> validationErrors;

    public ErrorObject(Integer statusCode, String message, Date timestamp, MethodArgumentNotValidException exception) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
        this.validationErrors = formatValidationErrors(exception);
    }

    private static Map<String, String> formatValidationErrors(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

}
