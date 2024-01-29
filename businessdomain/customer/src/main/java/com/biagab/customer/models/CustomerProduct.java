package com.biagab.customer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CustomerProduct {

    private long id;
    private long productId;
    private String productName;
    @JsonIgnore
    private Customer customer;

}
