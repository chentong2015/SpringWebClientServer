package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration2 {

    private static final String[] ENDPOINTS_WHITELIST = {"/css/**", "/", "/login", "/home"};
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_URL = "/logout";
    private static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    private static final String DEFAULT_SUCCESS_URL = "/home";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(request -> request.antMatchers(ENDPOINTS_WHITELIST).permitAll()
                        .anyRequest().authenticated())
                .csrf().disable()
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .loginProcessingUrl(LOGIN_URL)
                        .failureUrl(LOGIN_FAIL_URL)
                        .usernameParameter(USERNAME)
                        .passwordParameter(PASSWORD)
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL))
                .logout(logout -> logout
                        .logoutUrl(LOGOUT_URL)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl(LOGIN_URL + "?logout"));
        return http.build();
    }
}
