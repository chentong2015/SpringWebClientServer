package com.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring WebFlux 网络流量, 响应式编程，事件驱动
// reactive programming, non-blocking asynchronous applications and event-driven.
// https://josdem.io/techtalk/spring/spring_webflux_basics/

// 1. 和Spring MVC并行构建，实现的机制不同
// 2. Spring WebFlux supports fully non-blocking reactive streams. 响应式流
// 3. Spring WebFlux uses "Netty" as inbuilt server to run reactive applications. 通知机制
@SpringBootApplication
public class BaseSpringWebFlux {

    public static void main(String[] args) {
        SpringApplication.run(BaseSpringWebFlux.class, args);
    }
}
