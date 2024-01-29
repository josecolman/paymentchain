package com.biagab.customer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;
//import reactivefeign.spring.config.EnableReactiveFeignClients;
//import reactivefeign.spring.config.EnableReactiveFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "Customer management service",
				version = "1.0",
				description = "Rest api for Customer management."
		)
)
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableReactiveFeignClients
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
