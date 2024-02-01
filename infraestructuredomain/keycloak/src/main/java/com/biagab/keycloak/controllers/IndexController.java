package com.biagab.keycloak.controllers;

import com.auth0.jwk.Jwk;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.biagab.keycloak.services.JwtService;
import com.biagab.keycloak.services.KeycloakRestService;
import com.biagab.keycloak.utils.BusinessRuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private KeycloakRestService restService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(@RequestHeader("Authorization") String authorization) throws BusinessRuleException {
        try {
            DecodedJWT jwt = JWT.decode(authorization.replace("Bearer", "").trim());

            // check JWT is valid
            Jwk jwk = jwtService.getJwk();
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);

            algorithm.verify(jwt);

            // check JWT role is correct
            Object rolesObject = jwt.getClaim("realm_access").asMap().get("roles");

            List<?> rolesRaw = (List<?>) rolesObject;

            boolean allStrings = rolesRaw.stream().allMatch(e -> e instanceof String);
            if (!allStrings)
                throw new Exception("roles are not strings");

            List<String> roles = (List<String>) rolesRaw;

            // check JWT is still active
            Date expiryDate = jwt.getExpiresAt();
            if (expiryDate.before(new Date()))
                throw new Exception("token is expired");

            // all validation passed
            HashMap<String, Integer> HashMap = new HashMap<>();
            for (String str : roles) {
                HashMap.put(str, str.length());
            }
            return ResponseEntity.ok(HashMap);
        } catch (Exception e) {
            logger.error("exception : {} ", e.getMessage());
            throw new BusinessRuleException("01", e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/valid")
    public ResponseEntity<?> valid(@RequestHeader("Authorization") String authHeader) throws BusinessRuleException {
        try {
            restService.checkValidity(authHeader);
            return ResponseEntity.ok(new HashMap<String, String> (){{
                put("is_valid", "true");
            }});
        } catch (Exception e) {
            logger.error("token is not valid, exception : {} ", e.getMessage());
            throw new BusinessRuleException("is_valid", "false",HttpStatus.FORBIDDEN);

        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(String username, String password) {
        String login = restService.login(username, password);
        return ResponseEntity.ok(login);
    }

    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout(@RequestParam(value = "refresh_token", name = "refresh_token") String refreshToken) throws BusinessRuleException {
        try {
            restService.logout(refreshToken);
            return ResponseEntity.ok(new HashMap<String, String> (){{
                put("logout", "true");
            }});
        } catch (Exception e) {
            logger.error("unable to logout, exception : {} ", e.getMessage());
            throw new BusinessRuleException("logout", "False",HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refresh(@RequestParam(value = "refresh_token", name = "refresh_token") String refreshToken) throws BusinessRuleException {
        try {
            return ResponseEntity.ok(restService.refresh(refreshToken));
        } catch (Exception e) {
            logger.error("unable to refresh, exception : {} ", e.getMessage());
            throw new BusinessRuleException("refresh", "False",HttpStatus.FORBIDDEN);
        }
    }

}
