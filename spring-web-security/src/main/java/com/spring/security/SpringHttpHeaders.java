package com.spring.security;

import org.springframework.http.HttpHeaders;

public class SpringHttpHeaders {

    // Spring Web Http Header
    // 1. 所有关于Http头部的设置规则
    // 2. 添加认证的token
    private HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        // headers.setBearerAuth(token);
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }
}
