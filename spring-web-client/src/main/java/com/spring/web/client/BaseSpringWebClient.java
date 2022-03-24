package com.spring.web.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalTime;

// Spring WebClient (Reactive ClientRequest): 替代Spring AsyncRestTemplate/RestTemplate
public class BaseSpringWebClient {

    private final Logger logger = LogManager.getLogger(BaseSpringWebClient.class);

    // TODO: Client Side 客户端使用WebClient to consume a SSE endpoint
    // client.get()
    //    .uri("url")
    //    .accept(MediaType.TEXT_EVENT_STREAM)
    //    .retrieve()
    //    .bodyToFlux(Message::class.java)
    public void consumeServerSentEvent() {
        WebClient client = WebClient.create("http://localhost:8080/sse-server");

        // TypeReference类型和SSE Server端定义的返回类型是一致的
        ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<>() {
        };
        Flux<ServerSentEvent<String>> eventStream = client.get()
                .uri("/stream-sse")
                .retrieve()
                .bodyToFlux(type);  // 提供类型的映射

        // TODO: subscribe 订阅event事件，SSE Server端会进行事件的推送
        // 定义收到event事件，出错以及Completed完成之后如何处理
        eventStream.subscribe(
                content -> logger.info("Time: {} - event: name[{}], id [{}], content[{}] ",
                        LocalTime.now(), content.event(), content.id(), content.data()),
                error -> logger.error("Error receiving SSE: {}", error),
                () -> logger.info("Completed!!!"));
    }
}
