package com.spring.rest.template;

import org.springframework.web.client.RestTemplate;

public class BaseSpringRestTemplate {

    // RestTemplate提供的最常规的Web Request请求操作
    public void testGet() {
        final String uri = "http://localhost:8080/springrestexample/hello";
        RestTemplate restTemplate = new RestTemplate();
        String resultGet = restTemplate.getForObject(uri, String.class);

        String resultPost = restTemplate.postForObject(uri, "post item", String.class);
        restTemplate.put(uri, "put request data");
        restTemplate.delete(uri);
    }
}
