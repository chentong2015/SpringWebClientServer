package com.example.security.auth;

import org.springframework.http.HttpHeaders;

public class SpringHttpHeaders {

    // Spring Web Http Header
    // Http头部的设置规则并添加认证的token
    private HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        // headers.setBearerAuth(token);
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }
}
