package com.biagab.transaction.controllers;

import com.biagab.transaction.models.Transaction;
import com.biagab.transaction.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService service;

    @GetMapping
    public List<Transaction> list(@RequestParam String ibanAccount) {
        return service.list(ibanAccount);
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable long id) {
        return service.read(id);
    }

    @SneakyThrows
    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Transaction data, HttpServletRequest request) {

        long id = service.create(data);

        // Get base path from request
        return ResponseEntity.created(new URI(request.getRequestURI() + "/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Transaction data) {

        service.update(id, data);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }


}
