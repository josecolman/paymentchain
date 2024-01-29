package com.biagab.adminserver.configs;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;

//@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AdminServerProperties adminServer;

    public SecurityConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.getContextPath() + "/");

        http.authorizeHttpRequests(registry ->
                registry.requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/assets/**"))
                        .permitAll()
                        .requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/login"))
                        .permitAll()
                        .anyRequest()
                        .authenticated()

        ).authorizeHttpRequests(registry ->
                registry.requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/instances", HttpMethod.POST.toString()))
                        .permitAll()
                        .requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/instances/*", HttpMethod.DELETE.toString()))
                        .permitAll()
                        .requestMatchers(new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/**"))
                        .permitAll()
        ).formLogin(formLogin ->
                formLogin.loginPage(this.adminServer.getContextPath() + "/login")
                        .successHandler(successHandler)
        ).logout(logout ->
                logout.logoutUrl(this.adminServer.getContextPath() + "/logout")
        ).httpBasic(AbstractHttpConfigurer::disable
        ).csrf(csrf ->
                csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(this.adminServer.getContextPath() +
                                        "/instances", HttpMethod.POST.toString()),
                                new AntPathRequestMatcher(this.adminServer.getContextPath() +
                                        "/instances/*", HttpMethod.DELETE.toString()),
                                new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/**"))
        ).rememberMe(rememberMe ->
                rememberMe.key(UUID.randomUUID().toString())
                        .tokenValiditySeconds(1209600));


        return http.build();
    }*/


        /*http
                .authorizeRequests()
                .antMatchers(this.adminServer.getContextPath() + "/assets/**").permitAll()
                .antMatchers(this.adminServer.getContextPath() + "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(this.adminServer.getContextPath() + "/login")
                .successHandler(successHandler)
                .and()
                .logout()
                .logoutUrl(this.adminServer.getContextPath() + "/logout")
                .and()
                .httpBasic()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                        new AntPathRequestMatcher(this.adminServer.getContextPath() +
                                "/instances", HttpMethod.POST.toString()),
                        new AntPathRequestMatcher(this.adminServer.getContextPath() +
                                "/instances/*", HttpMethod.DELETE.toString()),
                        new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/**"))
                .and()
                .rememberMe()
                .key(UUID.randomUUID().toString())
                .tokenValiditySeconds(1209600);*/

}
