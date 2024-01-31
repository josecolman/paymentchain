package com.biagab.transaction.utils;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings("unused")
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleExceptions(NotFoundException e, WebRequest r) {

        log.error(e.getMessage());

        e.printStackTrace();

        StandardizedApiExceptionResponse response = StandardizedApiExceptionResponse.builder()
                .title(HttpStatus.NOT_FOUND.getReasonPhrase())
                .code(HttpStatus.NOT_FOUND.name())
                .detail(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<StandardizedApiExceptionResponse> handleExceptions(ValidationException e, WebRequest r) {

        log.error(e.getMessage());

        e.printStackTrace();

        StandardizedApiExceptionResponse response = StandardizedApiExceptionResponse.builder()
                .title("Validation error")
                .code(e.getCode())
                .detail(e.getMessage())
                .type("/errors/validation")
                .instance("/errors/validation/" + e.getCode())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler({ServerErrorException.class})
    public ResponseEntity<Object> handleExceptions(ServerErrorException e, WebRequest r) {

        log.error(e.getMessage());

        e.printStackTrace();

        StandardizedApiExceptionResponse response = StandardizedApiExceptionResponse.builder()
                .title("Server error")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .detail(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<StandardizedApiExceptionResponse> handleExceptions(Exception e, WebRequest r) {

        log.error(e.getMessage());

        e.printStackTrace();

        StandardizedApiExceptionResponse response = StandardizedApiExceptionResponse.builder()
                .title("Unexpected error")
                .code(HttpStatus.BAD_REQUEST.name())
                .detail(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
