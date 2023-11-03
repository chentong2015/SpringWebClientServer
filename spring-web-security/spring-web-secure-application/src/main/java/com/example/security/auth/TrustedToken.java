package com.example.security.auth;

public class TrustedToken {

    private String token;

    public TrustedToken(String token) {
        this.token = token;
    }

    public String getTokenString() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
