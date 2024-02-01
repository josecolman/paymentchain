package com.biagab.gateway.setups;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalPosFilter {

    @Bean
    public GlobalFilter globalFilter() {

        return (exchange, chain) -> {
            log.info("Global pos filter executing...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Global pos filter executed.");
            }));
        };

    }

}
