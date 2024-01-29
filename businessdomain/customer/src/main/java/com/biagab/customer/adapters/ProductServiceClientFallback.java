package com.biagab.customer.adapters;

import com.biagab.customer.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ProductServiceClientFallback implements ProductServiceClient {

    @Override
    public String getProductById(long id) {

        log.info("Fallback method for product-service: {}", id);

        return null;
    }

    @Override
    public Mono<Product> getProductFluxById(long id) {
        return null;
    }
}
