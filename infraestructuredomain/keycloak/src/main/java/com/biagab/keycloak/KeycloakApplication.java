package com.biagab.keycloak;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Keycloak authentication client service",
				version = "1.0",
				description = "Rest api for Keycloak authentication."
				//TODO no anda el swagger la cabecera Authorization
		)
)
@EnableDiscoveryClient
@SpringBootApplication
public class KeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakApplication.class, args);
	}

}
