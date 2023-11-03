package com.example.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

// 自定义对于Spring Security安全认证的实现
public class MyAuthentication implements Authentication {

    private String token;
    private transient Principal principal;
    private static final long serialVersionUID = -344227642091683711L;

    MyAuthentication(String token, Principal principal) {
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
