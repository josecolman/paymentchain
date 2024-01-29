package com.biagab.customer.services;

import com.biagab.customer.entities.CustomerEntity;
import com.biagab.customer.entities.CustomerProductEntity;
import com.biagab.customer.models.Customer;
import com.biagab.customer.models.CustomerProduct;
import com.biagab.customer.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    //import org.springframework.web.reactive.function.client.WebClient;
    //@LoadBalanced
    private final WebClient.Builder webClientBuilder;

    private final IProductService productService;

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



    public List<?> getTransactionsByCustomerIBAN(String iban) {

        String baseUrl = "http://transaction-service/api/v1/transaction";

        baseUrl = "http://localhost:8997/api/v1/transaction";

        WebClient webClient = webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //.defaultUriVariables(Collections.singletonMap("url", baseUrl))
                .build();

        List<?> result = webClient.get()
                .uri("?ibanAccount={iban}", iban)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();

        log.info("Result: " + result);

        return result;
    }

    public long create(Customer model) {
        CustomerEntity entity = mapToEntity(model);
        return customerRepository.save(entity).getId();
    }

    public Customer read(long id) {

        CustomerEntity entity = customerRepository.findById(id).orElse(null);

        if (entity == null)
            return null;

        if (entity.getProducts() != null) {

            entity.getProducts()
                    .forEach(product -> product.setProductName(productService.getProductById(product.getProductId())));

        }

        if (StringUtils.hasLength(entity.getIban())) {

            entity.setTransactions(
                    getTransactionsByCustomerIBAN(entity.getIban())
            );

        }

        return mapToModel(entity);
    }

    public void update(long id, Customer model) {
        CustomerEntity entity = customerRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(model, entity);
        customerRepository.save(entity);
    }

    public void delete(long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> list() {
        return customerRepository
                .findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public CustomerEntity mapToEntity(Customer model) {

        if (model == null)
            return null;

        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(model, entity);

        if (model.getProducts() != null) {

            entity.setProducts(model.getProducts().stream().map(product -> {

                CustomerProductEntity customerProductEntity = new CustomerProductEntity();
                BeanUtils.copyProperties(product, customerProductEntity);
                customerProductEntity.setCustomer(entity);
                return customerProductEntity;

            }).collect(Collectors.toList()));
        }

        /*if (model.getTransactions() != null) {

            entity.setTransactions(model.getTransactions().stream().map(transaction -> {

                CustomerTransactionEntity customerTransactionEntity = new CustomerTransactionEntity();
                BeanUtils.copyProperties(transaction, customerTransactionEntity);
                customerTransactionEntity.setCustomer(entity);
                return customerTransactionEntity;

            }).collect(Collectors.toList()));
        }*/

        return entity;
    }

    public Customer mapToModel(CustomerEntity entity) {

        if (entity == null)
            return null;

        Customer model = new Customer();
        BeanUtils.copyProperties(entity, model);

        if (entity.getProducts() != null) {

            model.setProducts(entity.getProducts().stream().map(product -> {

                CustomerProduct customerProduct = new CustomerProduct();
                BeanUtils.copyProperties(product, customerProduct);
                customerProduct.setCustomer(model);
                return customerProduct;

            }).collect(Collectors.toList()));
        }

        return model;
    }

}
