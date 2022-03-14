package com.spring.sse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Sever Send Event
// 1. 单向的事件流(Event Streaming), Server端一发送便能收到更新
// 2. Server端接受请求之后会保持open, 直到没有新的event剩下, 或者Timeout
// 3. 为了避免Connection Timeout, Server端会通过Heartbeat机制来维持
// 4. 如果timeout发生了，则需要重新建立连接retry

// Server-Sent Events (SSE) is a web technology
// 1. Provide one-way server-to-client communications, based on the existing HTTP protocol
// 2. Useful for sending notifications to clients or some like real time events update
//    in a stream approach such as news update or stock update
@SpringBootApplication
public class BaseSeverSendEvent {

    // TODO. SSE的两点主要特征 https://github.com/aliakh/demo-spring-sse
    // SSE network protocol
    // 1. data: The first event. 在data字段，server发送event时间的数据
    // 2. id: 1                  在连接端口，客户端自动重连时发送的最后一个接受到的id: Last-Event-ID
    //    data: The first event.
    // 3. event: type1           server发送不同的事件类型
    //    data: An event of type1
    // 4. retry: 1000            连接断开，设置client端将在多少时间内自动重连
    // 5. : ping                 以:开始会被client端忽略，server可以发送comments避免由于timeout造成的断连 !!

    public static void main(String[] args) {
        SpringApplication.run(BaseSeverSendEvent.class, args);
    }
}
