package com.biagab.customer.configs;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] ALLOWED_PATHS = {
            //"/v2/api-docs",             // Documentación de la API
            //"/swagger-resources/**",    // Recursos de Swagger
            //"/swagger-ui/**",           // Interfaz de usuario de Swagger
            "/actuator/**",             // Endpoints de Actuator
            "/h2-console/**",           // Consola de H2
    };


    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http) {

        http.authorizeHttpRequests(matchers ->

                matchers
                        .requestMatchers(ALLOWED_PATHS).permitAll()
                        .requestMatchers("/api/v1/account/register").permitAll()
                        .requestMatchers("/api/v1/account/auth").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users").hasRole("ADMIN")
                        .anyRequest().authenticated()
        );

        http.csrf(AbstractHttpConfigurer::disable);
        //http.cors(AbstractHttpConfigurer::disable);
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

       /* AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        authenticationManager = authenticationManagerBuilder.build();

        http.csrf().disable().cors().disable()
                .authorizeHttpRequests().antMatchers("/api/v1/account/register", "/api/v1/account/auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationManager(authenticationManager)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            var cors = new org.springframework.web.cors.CorsConfiguration();
            cors.setAllowedOrigins(List.of("*"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));

            cors.setAllowedHeaders(List.of(
                    "origin-accept",
                    "x-requested-with",
                    "origin",
                    "content-type",
                    "accept",
                    "accept-patch",
                    "content-type",
                    "access-control-request-method",
                    "access-control-request-headers"
            ));
            cors.setExposedHeaders(List.of(
                    //"access-control-allow-headers",
                    //"access-control-allow-methods",
                    "access-control-allow-origin",
                    "access-control-allow-credentials"
                    //"access-control-max-age",
                    //"x-requested-with"
            ));
            cors.setAllowedOriginPatterns(List.of("*"));
            cors.setMaxAge(Duration.ZERO);
            cors.setAllowCredentials(true);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", cors);

            return cors;
        };
    }

}
