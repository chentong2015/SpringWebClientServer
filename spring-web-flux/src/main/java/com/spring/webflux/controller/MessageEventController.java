package com.spring.webflux.controller;

import com.spring.webflux.model.Event;
import com.spring.webflux.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

// Create a controller to serve our stream data
// 测试效果，会不停的推送事件，增加在UI界面的显示
// data:{"nickname":"josdem","text":"Hola","timestamp":"2019-04-25T12:51:48.693987Z"}
// data:{"nickname":"josdem","text":"Bonjour","timestamp":"2019-04-25T12:51:49.692895Z"}
// data:{"nickname":"josdem","text":"Zdravstvuyte","timestamp":"2019-04-25T12:51:50.693260Z"}
@RestController
@RequiredArgsConstructor
public class MessageEventController {

    private final MessageService messageService;

    // MediaType.TEXT_EVENT_STREAM_VALUE represents plain text event sent that follows SSE format
    // responses starts with "data:"
    @GetMapping(path = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> index() {
        return messageService.stream();
    }
    
    // 直接通过ServerSentEvent构建, 不用再声明“text/event-stream” media type
    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .comment("some comments ...")
                        .build());
    }
}
