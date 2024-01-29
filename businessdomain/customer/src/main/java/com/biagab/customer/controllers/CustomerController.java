package com.biagab.customer.controllers;

import com.biagab.customer.models.Customer;
import com.biagab.customer.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @GetMapping()
    public List<Customer> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable Long id) {
        Customer data = service.read(id);
        if (data == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(data);
    }

    @SneakyThrows
    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Customer data, HttpServletRequest request) {

        long id = service.create(data);

        // Get base path from request
        return ResponseEntity.created(new URI(request.getRequestURI() + "/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Customer data) {

        service.update(id, data);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }


}
