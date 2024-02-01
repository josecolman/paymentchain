package com.biagab.keycloak.adapters;

import org.keycloak.TokenVerifier;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Component;

@Component
public class KeycloakClientAdapter {

    /*private static final String KEYCLOAK_SERVER_URL = "http://localhost:8080/auth";
    private static final String REALM_NAME = "your-realm";
    private static final String CLIENT_ID = "your-client-id";
    private static final String CLIENT_SECRET = "your-client-secret";*/

    /*private final Keycloak keycloak;

    public KeycloakClientAdapter(Keycloak keycloak) {
        this.keycloak = keycloak;
    }*/


    public boolean validateToken(
            String accessToken,
            String keycloakServerUrl,
            String realmName,
            String clientId,
            String clientSecret
    ) throws VerificationException {
        // Construir la instancia de Keycloak
       /* try (Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm(realmName)
                .clientId(clientId)
                .clientSecret(clientSecret)
                //.username("admin")
                //.password("admin")
                .build()) {*/

            //RealmResource realmResource = keycloak.realm(realmName);
            //boolean isValid = realmResource.
            //realmResource.users().get("user-id").roles();

            // Crear un token verifier para verificar el token
            TokenVerifier<AccessToken> verifier = TokenVerifier.create(accessToken, AccessToken.class);


            // Configurar el chequeo de token
            //AccessToken token = verifier.getToken();
            //boolean isValid = token.isActive() && token.verify();
            boolean isValid = verifier.verify().getToken().isActive();
            return isValid;


          /*  Keycloak keycloakClient = KeycloakBuilder.builder()
                    .serverUrl(keycloakServerUrl)
                    .realm(realmName)
                    .grantType("client_credentials")
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .accessToken(accessTokenToValidate)
                    .build();

            RealmResource realmResource = keycloakClient.realm(realmName);
            boolean isValid = realmResource.introspectToken(accessTokenToValidate).isActive();*/


     /*   }catch (Exception e) {
            e.printStackTrace();
            return false;
        }*/



        // Obtener el token de administrador para autenticar
        /*AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();

        // Construir la instancia del cliente Keycloak con el token de administrador
        Keycloak keycloakClient = KeycloakBuilder.builder()
                .serverUrl(KEYCLOAK_SERVER_URL)
                .realm(REALM_NAME)
                .grantType("client_credentials")
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .accessToken(accessTokenResponse.getToken())
                .build();

        // Realizar la validación del token
        String accessTokenToValidate = "your-access-token-to-validate";
        RealmResource realmResource = keycloakClient.realm(REALM_NAME);
        boolean isValid = realmResource.introspectToken(accessTokenToValidate).isActive();

        if (isValid) {
            System.out.println("El token es válido.");
        } else {
            System.out.println("El token no es válido.");
        }*/
    }

}
