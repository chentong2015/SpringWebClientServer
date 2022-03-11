package com.spring.sse;

// Spring Sever Send Event
// 1. 单向的事件流(Event Streaming)，Server端一发送便能收到更新
// 2. Server端接受请求之后，会保持open，直到没有新的event剩下， 或者Timeout
// 3. 为了避免Connection Timeout, Server端会通过Heartbeat机制来维持
// 4. 如果timeout发生了，则需要重新建立连接

// SSE provides one-way server-to-client communications, based on the existing HTTP protocol
// Useful for sending notifications to clients or some like real time events update in a stream approach
// such as news update or stock update

// TODO: https://github.com/aliakh/demo-spring-sse
public class BaseSeverSendEvent {

}
