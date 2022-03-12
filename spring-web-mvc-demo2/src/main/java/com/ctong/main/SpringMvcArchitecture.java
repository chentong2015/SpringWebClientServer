package com.ctong.main;

public class SpringMvcArchitecture {

    // TODO: 没有找到webapp中的文件，DispatcherServlet没有完成请求的分发 !!
    // Spring MVC Request processing: 参考Spring文档 16.2 The DispatcherServlet

    //                              <->  Handler Mapping
    //                              <->  Handler Adapter (adapter接口的实现)
    // request -> DispatcherServlet <->  View resolver    -> Interceptor (preHandle方法) (HandlerInterceptor接口的实现)
    //                                                    -> Controller  ->  Service Layer  -> Day Layer  ->  Database
    //                                                    -> Interceptor (postHandle方法)
    //                                                    -> View template (适用model渲染结果)
    //                     Response <->  Web Page         <- View Engine (渲染引擎)

    // 1. HandlerMapping: 通过HandlerMapping的匹配选择, Servlet分发器将request分发到相关的components(Controller)的方法
    // .                  直接会查找所有标记@Controller beans中的@RequestMapping annotations

    // 2. HandlerAdapter: Maps a handler method to specific URL
    //.                   DispatcherServlet不直接触发方法，而是使用HandlerAdapter来invoke方法 !!

    // 3. Interceptor: 详见 spring-boot-maven module
}
