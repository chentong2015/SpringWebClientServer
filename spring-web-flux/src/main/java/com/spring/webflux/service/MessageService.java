package com.spring.webflux.service;

import com.spring.webflux.model.Event;
import reactor.core.publisher.Flux;

public interface MessageService {

    Flux<Event> stream();
}
