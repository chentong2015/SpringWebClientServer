package com.spring.sse.demo.service;

import com.spring.sse.demo.model.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;

@Service
public class MessageService {

    private final Sinks.Many<Message> sinks = Sinks.many().replay().latest();

    public Flux<Message> latestMessages() {
        return sinks.asFlux();
        //  return Flux.<Message>create((fluxSink) ->
        //  emitters.put("user", fluxSink.onDispose(() -> this.emitters.remove("user"))), OverflowStrategy.BUFFER);
    }

    public void send(Message m) {
        sinks.emitNext(m, EmitFailureHandler.FAIL_FAST);
        // this.emitters.values().forEach(emitter -> emitter.next(m));
    }
}
