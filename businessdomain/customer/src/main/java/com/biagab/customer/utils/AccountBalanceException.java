package com.biagab.customer.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AccountBalanceException extends Exception {

    private String code;
    private HttpStatus httpStatus;
}
