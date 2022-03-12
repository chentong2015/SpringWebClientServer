package main.controller.rest;

// SOAP: Simple Object Access Protocol
// 1. 目的是访问隐藏在Web外观背后的对象，以及调用这些对象的操作
// 2. 本质是执行RPC
// 3. SOAP实现中只使用了HTTP动词的GET,POST

// 设计RESTFul接口: 能够提供一种整洁组织API的方式
// 1. REST指应用程序将以HTTP动词(GET,POST,PUT,DELETE,HEAD)操作资源的形式来处理请求
// 2. REST是Web上面的GRUD, 处理的是有URI标识的资源
// 3. REST期望HTTP响应声明其可缓存性
public class BaseRestfulAPI {

    // 1. GET    发出请求来获取指定的资源(的主体)
    // 2. HEAD   和GET相同，获取指定的资源，但返回的是指定资源的"元数据"，检测某个资源的存在性
    // 3. POST   发送一个"添加资源"的请求，带参数。操作会创建新的资源
    // 4. PUT    发送请求，确保指定资源的状态是符合提供的信息，同时视为"更新命令"的逻辑
    // 5. DELETE 删除指定的资源，物理或者逻辑删除

    // 返回状态码
    // 200 所执行的任何操作都成功了
    // 204 成功，但是响应体为空的响应 ==> 等效于状态码200+一个可选的空响应体
    // 404 找不到资源
    // 401 未获授权，或者提供一个更具体的错误代码
    // 500 发生错误，或者提供一个更具体的错误代码
}
