package com.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

// Spring Authentification 用户登录和认证的问题
// https://spring.io/projects/spring-security-oauth 已经废弃了 !!
// Autorisation
// Permission
// Client ID
// Authentification Filter ==> 用户身份的管理和认证
// Use Session ==> 通过session来保证用户是否登录  https://spring.io/projects/spring-session-core
// https://spring.io/guides/tutorials/spring-boot-oauth2/

// 自定义对于Spring Security安全认证的实现
public class MySpringAuthentication implements Authentication {

    private String token;
    private transient Principal principal;
    private static final long serialVersionUID = -344227642091683711L;

    MySpringAuthentication(String token, Principal principal) {
        this.token = token;
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Cannot update authentication state");
    }

    @Override
    public String getName() {
        return null;
    }
}
