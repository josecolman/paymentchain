package com.biagab.customer.services;

import com.biagab.customer.adapters.TransactionServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@RefreshScope
public class TransactionService implements ITransactionService {

    private final TransactionServiceClient transactionServiceClient;

    @Override
    public List<?> getTransactionsByCustomerIBAN(String iban) {
        return transactionServiceClient.getTransactionsByCustomerIBAN(iban);
    }

    /*@Value("${transaction.service.url}")
    private String transactionServiceUrl;


    @Bean
    public WebClient webClient() {

        String baseUrl = transactionServiceUrl + "/api/v1/transaction";

        return loadBalancedWebClientBuilder()
                .baseUrl(baseUrl)
                .filter(lbFunction)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", baseUrl))
                .build();
    }

    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    public List<?> getTransactionsByCustomerIBAN(String iban) {

        //List<?> result = webClient.get()
        List<?> result = webClient().get()
                .uri("?ibanAccount={iban}", iban)
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();

        log.info("Result: " + result);

        return result;
    }*/

}
