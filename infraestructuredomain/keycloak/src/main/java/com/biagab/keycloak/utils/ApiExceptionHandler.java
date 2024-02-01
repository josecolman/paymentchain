package com.biagab.keycloak.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleUnknownHostException(UnknownHostException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de conexion", "error-1024",ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleBusinessRuleException(BusinessRuleException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de validacion", ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleBusinessRuleException(Exception ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error", "error-1000", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
