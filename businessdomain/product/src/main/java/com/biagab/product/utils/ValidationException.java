package com.biagab.product.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ValidationException extends Exception {

    private String code = "DDE-001";
    private HttpStatus httpStatus;

    public ValidationException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
