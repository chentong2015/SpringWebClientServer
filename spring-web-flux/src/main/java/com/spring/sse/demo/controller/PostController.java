package com.spring.sse.demo.controller;

import com.spring.sse.demo.dao.PostRepository;
import com.spring.sse.demo.model.Post;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    private final PostRepository posts;

    public PostController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Post> all() {
        return this.posts.findAll();
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Post> sse() {
        return Flux.zip(Flux.interval(Duration.ofSeconds(1)), this.posts.findAll().repeat())
                .map(Tuple2::getT2);
    }

    @GetMapping(value = "/latest", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Post> trackLatestPost() {
        return this.posts.latestPost();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Post> create(@RequestBody Post post) {
        return this.posts.save(post);
    }

}
