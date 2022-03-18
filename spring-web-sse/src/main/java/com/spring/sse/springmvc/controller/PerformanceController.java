package com.spring.sse.springmvc.controller;

import com.spring.sse.springmvc.emitter.SseEmitters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/sse/mvc")
public class PerformanceController {

    private final AtomicInteger id = new AtomicInteger();
    private final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
    private final SseEmitters emitters = new SseEmitters();

    @PostConstruct
    public void init() {
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            emitters.send("performanceService.getPerformance()");
        }, 0, 1, TimeUnit.SECONDS);
    }

    @GetMapping(path = "/performance", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getPerformance() {
        return emitters.add(new SseEmitter());
    }
}
