package com.example.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
public class SecurityConfigurationMaster {

    // 忽略访问Endpoints的安全性，定义不需要做Filter的endpoints
    // @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/library/info");
    }

    // @Bean
    // @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(request -> request.antMatchers("/home")
                        .permitAll().anyRequest().authenticated())
                .csrf().disable()
                .antMatcher("/login")
                .formLogin(form -> form
                        .loginPage("login")
                        .loginProcessingUrl("login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("DEFAULT_SUCCESS_URL"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/logoutok"));
        return http.build();
    }


    // 特定义用户认证的过滤器，配置当访问"login"节点时需要进行用户认证
    // @Bean
    // private AuthenticationFilter authenticationFilter(HttpSecurity http) {
    //    AuthenticationFilter filter = new AuthenticationFilter(resolver(http), authenticationConverter());
    //    filter.setSuccessHandler((request, response, auth) -> {
    //    });
    //    return filter;
    // }
    //
    // public AuthenticationConverter authenticationConverter() {
    //    return new BasicAuthenticationConverter();
    // }
    //
    // public AuthenticationManagerResolver<HttpServletRequest> resolver(HttpSecurity http) {
    //    return request -> {
    //        if (request.getPathInfo().contains("login")) {
    //            try {
    //                return customAuthenticationManager(http);
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return null;
    //    };
    // }
    //
    // public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
    //    return http.getSharedObject(AuthenticationManagerBuilder.class)
    //            .userDetailsService(userDetailsService())
    //            .passwordEncoder(passwordEncoder())
    //            .and()
    //            .build();
    // }
    //
    // public PasswordEncoder passwordEncoder() {
    //    return new BCryptPasswordEncoder();
    // }
}
