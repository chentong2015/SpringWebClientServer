package com.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

// Spring Authentification 用户登录和认证的问题
// 1. client id
// 2. client permission
// 3. authentification Filter
// 4. session 保证用户是否登录
// https://spring.io/projects/spring-session-core
// https://spring.io/guides/tutorials/spring-boot-oauth2/
public class BaseSpringSecurity {

    public void handleAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            System.out.println("missing authentication");
        }
        Object principal = authentication.getPrincipal();
        Principal myPrincipal = (Principal) principal;
        // 从Principal中获取指定的安全信息
    }
}
