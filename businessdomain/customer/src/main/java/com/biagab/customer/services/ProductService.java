package com.biagab.customer.services;

import com.biagab.customer.adapters.ProductServiceClient;
import com.biagab.customer.adapters.ProductServiceReactiveClient;
import com.biagab.customer.models.Product;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
//import reactivefeign.webclient.WebReactiveFeign;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService implements IProductService{

    //import org.springframework.web.reactive.function.client.WebClient;
    //@LoadBalanced
    private final WebClient.Builder webClientBuilder;
    private final ProductServiceClient productServiceClient;
    private final ProductServiceReactiveClient productServiceReactiveClient;

    @Bean
    public ProductServiceReactiveClient productServiceClient() {
        return null; // No es necesario crear una instancia aquí, Feign lo manejará.
    }

    /*
    //Create instance of your API
    IcecreamServiceApi client =
            WebReactiveFeign  //WebClient based reactive feign
                    //JettyReactiveFeign //Jetty http client based
                    //Java11ReactiveFeign //Java 11 http client based
                    .<IcecreamServiceApi>builder()
                    .target(IcecreamServiceApi.class, "http://www.icecreame.com")

    // Execute nonblocking requests
    Flux<Flavor> flavors = icecreamApi.getAvailableFlavors();
    Flux<Mixin> mixins = icecreamApi.getAvailableMixins();
     */
    /*ProductServiceReactiveClient reactiveClient =
            WebReactiveFeign  //WebClient based reactive feign
                    //JettyReactiveFeign //Jetty http client based
                    //Java11ReactiveFeign //Java 11 http client based
                    .<ProductServiceReactiveClient>builder()
                    .target(ProductServiceReactiveClient.class, "http://localhost:8999");*/

    private final ProductServiceReactiveClient reactiveClient;

    HttpClient httpClient = HttpClient.create()
            //.wiretap(true)
            //.compress(true)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .responseTimeout(java.time.Duration.ofMillis(10000))
            .option(ChannelOption.SO_KEEPALIVE, true)
            //.option(EpollChannelOption.TCP_KEEPIDLE, 600)
            //.option(EpollChannelOption.TCP_KEEPINTVL, 60)
            .doOnConnected(connection -> {
                connection.addHandlerFirst(new ReadTimeoutHandler(10, TimeUnit. SECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(10, TimeUnit. SECONDS));
            });

    public String getProductByIdByHttp(long id) {

        String baseUrl = "http://product-service/api/v1/product";

        // baseUrl = "http://localhost:8999/api/v1/product";

        WebClient webClient = webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", baseUrl))
                .build();

        JsonNode result = webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block()
                ;

        if( result == null)
            return null;

        log.info("Result: " + result);

        if (!result.has("name"))
            return null;

        String productName = result.get("name").asText();
        log.info("Product Name: " + productName);
        return productName;

    }

    public String getProductByIdByFeign(long id){
        return productServiceClient.getProductById(id);
    }

    public String getProductById(long id){

        /*productServiceClient.getProductFluxById(id)
                .subscribe(product -> {
                    log.info("Product: " + product);
                });*/

       /* Mono<Product> productMono = reactiveClient.getProductById(id);
        productMono.flatMap(product -> {
            // Realiza cualquier manipulación necesaria con el producto
            // Devuelve el producto manipulado dentro del Mono
            return Mono.just(product);
        });
        productMono.subscribe(product -> {
            log.info("Product: " + product);
        });*/

       /* productServiceClient.getProductFluxById(id)
                .subscribe(product -> {
                    log.info("Product: " + product);
                });*/


        return productServiceClient.getProductById(id);
    }

}
