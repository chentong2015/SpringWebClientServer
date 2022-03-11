package com.spring.sse.demo.controller;

import com.spring.sse.demo.model.Message;
import com.spring.sse.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> messageStream() {
        return messageService.latestMessages();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> send(@RequestBody String request) {
        Message message = Message.builder()
                .id(UUID.randomUUID())
                .body(request)
                .sentAt(LocalDateTime.now()).build();
        messageService.send(message);
        return Mono.just(created(URI.create("/messages/" + message.getId())).build());
    }
}
