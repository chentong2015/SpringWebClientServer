package com.example.https.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Block any request coming from a non-secure HTTP channel and redirect them to HTTPS
    //
    // http://localhost:443 -> https://localhost:443 自动重定向访问
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .authorizeRequests(authorize -> authorize.anyRequest().permitAll())
                .build();
    }
}
