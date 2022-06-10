package main;

/**
 * DispatcherServlet: Dispatcher"服务程序", 置于Web container中
 * 1. Front com.ctong.springboot.controller of Spring MVC, it receives all the requests, dispatch/send HTTP request 分发HTTP的请求
 * 2. Spring MVC Central Servlet, provides a shared algorithm for request processing 提供请求处理的共享算法
 */
public class DispatcherServlet {

    /**
     * Configure the dispatcher's Servlet (to use the webApplicationContext container and WebApplicationConfig Class)
     * 1. Add Servlet API Dependency
     * 2. <scope>provided</scope>
     * ---  通过Servlet container(这里使用Tomcat)来提供所需dependency, 而不用打包到war file文件中, 减少部署的文件大小 !!
     * ---  The dependency will be provided by the container at runtime 在运行的时候依赖会被提供 !!
     * ---  部署到Web Container => Tomcat 将会在runtime时提供依赖
     */

    // Interface WebApplicationInitializer 官方API
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/WebApplicationInitializer.html
    /**
     * Servlet Registration
     * 1. 实现Web Application Initializer Interface接口类通过程序注册
     * 2. ApplicationInitializer会在App启动的时候自动被Spring检测, 并执行实现的方法
     *  Interface provided by Spring MVC that ensures your implementation is detected and automatically used to initialize any Servlet 3 container
     */

    /**
     * 为什么容器启动之后会调用ApplicationInitializer的onStartup()方法
     * 1. 通过类型SpringServletContainerInitializer实现ServletContainerInitializer接口，该接口允许通过网络通知库/运行时应用程序的启动阶段, 执行所需要的
     * -- registration of servlets
     * -- filters
     * -- listen to response
     * 2. @HandlesTypes: 声明ServletContainerInitializer接口能够处理的类型
     */
}
