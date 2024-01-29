package com.biagab.product.controllers;

import com.biagab.product.models.Product;
import com.biagab.product.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<Product> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        return service.read(id);
    }

    @SneakyThrows
    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Product data, HttpServletRequest request) {

        long id = service.create(data);

        // Get base path from request
        return ResponseEntity.created(new URI(request.getRequestURI() + "/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Product data) {

        service.update(id, data);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }


}
