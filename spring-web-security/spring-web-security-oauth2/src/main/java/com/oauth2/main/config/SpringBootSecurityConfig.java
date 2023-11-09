package com.oauth2.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// For spring boot application
@Configuration
@EnableWebSecurity
public class SpringBootSecurityConfig {

    // 常用Login登录方法: .httpBasic(), .fromLogin(), .oauth2Login()
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2Login();
        return http.build();
    }
}
