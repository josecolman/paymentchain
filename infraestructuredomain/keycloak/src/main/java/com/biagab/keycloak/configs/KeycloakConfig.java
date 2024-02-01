package com.biagab.keycloak.configs;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.server-uri}")
    private String keycloakServerUri;

    private String keycloakRealm = "PaymentChain";

    @Value("${keycloak.authorization.grant-type}")
    private String grantType;

    @Value("${keycloak.authorization.grant-type-refresh}")
    private String grantTypeRefresh;

    @Value("${keycloak.client.id}")
    private String clientId;

    @Value("${keycloak.client.secret}")
    private String clientSecret;

    @Value("${keycloak.scope}")
    private String scope;

    /*@Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServerUri)
                .realm(keycloakRealm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                //.username("baeldung")
                //.password(clien)
                .build();
    }*/

}
