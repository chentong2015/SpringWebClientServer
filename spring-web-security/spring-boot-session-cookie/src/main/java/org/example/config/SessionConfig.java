package org.example.config;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SessionConfig {

    // TODO. 这里定义Filter必须满足指定的Order先后顺序
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new CookieSessionFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }

    // TODO. 自定义有关Session的过滤器
    // 只需配置一次Session相关的属性: 过期时间以及安全性
    static class CookieSessionFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            Cookie[] allCookies = req.getCookies();
            if (allCookies != null) {
                Cookie session = getSessionFromCookies(allCookies);
                if (session != null) {
                    session.setMaxAge(100); // 100s
                    session.setHttpOnly(false);
                    session.setSecure(true);
                    res.addCookie(session);
                }
            }
            chain.doFilter(req, res);
        }

        private Cookie getSessionFromCookies(Cookie[] cookies) {
            return Arrays.stream(cookies).filter(x -> x.getName().equals("JSESSIONID"))
                    .findFirst().orElse(null);
        }
    }
}
