package com.biagab.product.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Product", description = "Product domain model")
@Data
public class Product {

    @Schema(name = "id", example = "1", defaultValue = "0", description = "Unique Id of product")
    private long id;
    @Schema(name = "name", example = "Product 1", defaultValue = "Product 1", description = "Name of product")
    private String name;
    @Schema(name = "code", example = "P1", defaultValue = "P1", description = "Code of product")
    private String code;

}
