package com.spring.sse.webflux.service;

import com.spring.sse.webflux.model.Event;
import reactor.core.publisher.Flux;

public interface MessageService {

    Flux<Event> stream();
}
