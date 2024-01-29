package com.biagab.customer.adapters;


import com.biagab.customer.models.Product;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

//https://github.com/PlaytikaOSS/feign-reactive
@Component
//@ReactiveFeignClient(name = "product-service-reactive", url = "http://localhost:8999")
@FeignClient(name = "product-service-reactive", url = "http://localhost:8999")
//spring-cloud-starter-openfeign
//@Headers({ "Accept: application/json" })
public interface ProductServiceReactiveClient {

    //@RequestLine("GET /icecream/flavors")
    //Flux<Flavor> getAvailableFlavors();

    //@RequestLine("GET /icecream/mixins")
    //Flux<Mixin> getAvailableMixins();

    //@RequestLine("POST /icecream/orders")
    //@Headers("Content-Type: application/json")
    //Mono<Bill> makeOrder(IceCreamOrder order);

    //@RequestLine("GET /icecream/orders/{orderId}")
    //Mono<IceCreamOrder> findOrder(@Param("orderId") int orderId);
    //@RequestLine("GET /api/v1/product/{id}")
    @GetMapping("/api/v1/product/{id}")
    Mono<Product> getProductById(@PathVariable("id") long id);

    //@RequestLine("POST /icecream/bills/pay")
    //@Headers("Content-Type: application/json")
    //Mono<Void> payBill(Publisher<Bill> bill);
}
