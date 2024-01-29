package com.biagab.customer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {

    @Bean
    //@LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    public List<?> getTransactionsByCustomerIBAN(String iban) {

        String baseUrl = "http://transaction-service/api/v1/transaction";

        //baseUrl = "http://localhost:8997/api/v1/transaction";
        baseUrl = "http://transaction-service/api/v1/transaction";


       /* WebClient webClient = webClientBuilder
                //.clientConnector(new ReactorClientHttpConnector(httpClient))
                .clientConnector(new ReactorClientHttpConnector())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //.defaultUriVariables(Collections.singletonMap("url", baseUrl))
                .build();*/

        WebClient webClient = loadBalancedWebClientBuilder()
                .baseUrl(baseUrl)
                .filter(lbFunction)
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

}
