package com.spring.sse.webflux.service;

import com.spring.sse.webflux.model.Performance;

public class PerformanceService {

    public static Performance getPerformance() {
        return new Performance();
    }
}
