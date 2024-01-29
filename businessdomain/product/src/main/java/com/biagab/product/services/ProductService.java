package com.biagab.product.services;

import com.biagab.product.entities.ProductEntity;
import com.biagab.product.models.Product;
import com.biagab.product.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public long create(Product model) {
        ProductEntity entity = mapToEntity(model);
        return productRepository.save(entity).getId();
    }

    public Product read(long id) {
        ProductEntity entity = productRepository.findById(id).orElse(null);
        return mapToModel(entity);
    }

    public void update(long id, Product model) {
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
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public ProductEntity mapToEntity(Product model) {
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
    }

}
