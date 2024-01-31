package com.biagab.customer.adapters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class TransactionServiceClient {

    @Value("${transaction.service.url}")
    private String transactionServiceUrl;

    private final WebClient.Builder webClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    @Bean
    public WebClient webClient() {

        String baseUrl = transactionServiceUrl + "/api/v1/transaction";

        return webClientBuilder
                .baseUrl(baseUrl)
                .filter(lbFunction)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", baseUrl))
                .build();
    }

    public List<?> getTransactionsByCustomerIBAN(String iban) {

        List<?> result = webClient().get()
                .uri("?ibanAccount={iban}", iban)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();

        log.info("Result: " + result);

        return result;
    }

}
