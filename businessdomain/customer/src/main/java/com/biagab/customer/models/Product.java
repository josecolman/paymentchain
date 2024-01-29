package com.biagab.customer.models;

import com.biagab.customer.MonoDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@JsonDeserialize(using = MonoDeserializer.class)
@Data
public class Product {

    private long id;
    private String name;
    private String code;

}
