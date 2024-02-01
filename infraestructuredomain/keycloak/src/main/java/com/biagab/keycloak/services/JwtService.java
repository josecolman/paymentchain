package com.biagab.keycloak.services;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.UrlJwkProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class JwtService {

    @Value("${keycloak.jwk-set-uri}")
    private String jwkUrl;

    @Value("${keycloak.certs-id}")
    private String certsId;

    @Cacheable(value = "jwkCache")
    public Jwk getJwk() throws MalformedURLException, JwkException {
        URL url = ResourceUtils.toURL(jwkUrl);
        UrlJwkProvider urlJwkProvider = new UrlJwkProvider(url);
        return urlJwkProvider.get(certsId.trim());
    }

}
