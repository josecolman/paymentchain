package com.biagab.customer.utils;

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

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                StandardizedApiExceptionResponse.builder().title(e.getMessage()).code(HttpStatus.NOT_FOUND.value())
        );
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleExceptions(BadRequestException e, WebRequest r) {

        log.error(e.getMessage());

        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                StandardizedApiExceptionResponse.builder().title(e.getMessage()).code(HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler({ServerErrorException.class})
    public ResponseEntity<Object> handleExceptions(ServerErrorException e, WebRequest r) {

        log.error(e.getMessage());

        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                StandardizedApiExceptionResponse
                        .builder()
                        .title(e.getMessage())
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<StandardizedApiExceptionResponse> handleExceptions(Exception e, WebRequest r) {

        log.error(e.getMessage());

        e.printStackTrace();

        /*return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(
                StandardizedApiExceptionResponse.builder().title(e.getMessage()).code(HttpStatus.PARTIAL_CONTENT.value())
        );*/

        StandardizedApiExceptionResponse response = StandardizedApiExceptionResponse.builder()
                .title("Validation error")
                //.code(e.hashCode())
                .code(HttpStatus.BAD_REQUEST.value())
                .detail(e.getMessage())
                .build();

        //return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
