package com.spring.sse.springmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseWebMvcController {

    private SseEmitter emitter;

    @GetMapping(path = "/sse/creation", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    SseEmitter createConnection() {
        emitter = new SseEmitter();
        return emitter;
    }

    // Send events asynchronously, in another thread
    // 调用.send()多次发送信息
    void sendEvents() {
        try {
            emitter.send("Alpha");
            emitter.send("Omega"); // 只发送data field
            // emitter.send(SseEmitter.SseEventBuilder builder) 支持发送多个fields

            emitter.complete();
        } catch (Exception e) {
            emitter.completeWithError(e);
        }
    }
}
