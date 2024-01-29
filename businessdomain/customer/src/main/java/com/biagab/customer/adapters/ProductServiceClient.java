package com.biagab.customer.adapters;

import com.biagab.customer.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@FeignClient(name="product-service",
        //url = "${product.service.url}",
        fallback = ProductServiceClientFallback.class)
public interface ProductServiceClient {

    @GetMapping("/api/v1/product/{id}")
    //@RequestMapping(method = RequestMethod.GET, value ="/api/v1/product/{id}")
    String getProductById(@PathVariable long id);


    @GetMapping("/api/v1/product/{id}")
        //@RequestMapping(method = RequestMethod.GET, value ="/api/v1/product/{id}")
    Mono<Product> getProductFluxById(@PathVariable long id);

}
