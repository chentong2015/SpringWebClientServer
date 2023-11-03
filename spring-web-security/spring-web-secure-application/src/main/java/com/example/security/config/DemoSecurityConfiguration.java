package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
public class DemoSecurityConfiguration {

    // 授权全部的请求以及所用用户的认证
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.requestMatchers().permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    // 根据不同的URL Request定义不同的授权Authorization
    @Bean
    public SecurityFilterChain bookFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/library/info").permitAll()
                .antMatchers(HttpMethod.GET, "/library/books").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/library/books/all").hasRole("ADMIN");
        return http.build();
    }

    // 直接在代码中定义用于验证的用户身份信息，保存在内存中InMemory
    // 定义用户的角色(Role)和授权的内容
    @Bean
    public UserDetailsService userDetailsService() {
        User.withUsername("chen").password("password").build();
        UserDetails user = User.withUsername("username")
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles("USER")
                .authorities(new SimpleGrantedAuthority("READ"))
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
