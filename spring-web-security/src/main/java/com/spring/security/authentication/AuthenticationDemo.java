package com.spring.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class AuthenticationDemo {

    // Spring Authentication
    // 1. client id
    // 2. client permission
    // 3. authentication Filter
    // 4. session 保证用户是否登录
    public void handleAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            System.out.println("missing authentication");
        }

        // 从Principal中获取指定的安全信息
        Object principal = authentication.getPrincipal();
        Principal myPrincipal = (Principal) principal;
    }
}
