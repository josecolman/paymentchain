package com.biagab.product.mappers;

import com.biagab.product.entities.ProductEntity;
import com.biagab.product.models.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "code", source = "code")
    })
    ProductEntity dtoToEntity(Product source);

    @InheritInverseConfiguration
    Product entityToDTO(ProductEntity source);

    List<ProductEntity> dtoToEntity(List<Product> source);

    List<Product> entityToDTO(List<ProductEntity> source);
}
