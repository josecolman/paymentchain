package com.biagab.product.services;

import com.biagab.product.entities.ProductEntity;
import com.biagab.product.mappers.ProductMapper;
import com.biagab.product.models.Product;
import com.biagab.product.repositories.ProductRepository;
import com.biagab.product.utils.ValidationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @SneakyThrows
    public long create(Product model) {

        validate(model);

        //ProductEntity entity = mapToEntity(model);
        ProductEntity entity = productMapper.dtoToEntity(model);

        return productRepository.save(entity).getId();
    }

    private void validate(Product model) throws ValidationException {
        if (model == null)
            throw new ValidationException("DDE-001", "Product cannot be null", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasLength(model.getCode()))
            throw new ValidationException("DDE-002", "Product code cannot be longer than 10 characters", HttpStatus.BAD_REQUEST);

        if (!StringUtils.hasLength(model.getName()))
            throw new ValidationException("DDE-003", "Product name cannot be longer than 100 characters", HttpStatus.BAD_REQUEST);
    }

    public Product read(long id) {
        ProductEntity entity = productRepository.findById(id).orElse(null);
        //return mapToModel(entity);
        return productMapper.entityToDTO(entity);
    }

    @SneakyThrows
    public void update(long id, Product model) {

        validate(model);

        ProductEntity entity = productRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(model, entity);
        productRepository.save(entity);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> list() {
        return productRepository
                .findAll()
                .stream()
                //.map(this::mapToModel)
                .map(productMapper::entityToDTO)
                .collect(Collectors.toList());
    }

   /* public ProductEntity mapToEntity(Product model) {
        if (model == null)
            return null;
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    public Product mapToModel(ProductEntity entity) {
        if (entity == null)
            return null;
        Product model = new Product();
        BeanUtils.copyProperties(entity, model);
        return model;
    }*/

}
