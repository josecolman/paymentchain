package com.biagab.product.utils;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {
    private String message;
    private Integer statusCode;
    private String orderNumber;
}
