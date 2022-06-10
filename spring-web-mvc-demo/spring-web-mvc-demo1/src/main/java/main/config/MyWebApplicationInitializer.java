package main.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * ServletContext  ====> 等效使用web.xml配置
 * 1. 一个项目只有一个Servlet Context, 代表了server container
 * 2. 定义了一系列和Servlet Container(这里使用Tomcat)交互的方法 !!
 * 3. 在部署Project的时候由Web Container创建(实例化和初始化)，并且自动的调用onStartup()
 */
@Order(value = 10)
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("Start application ...");

        // 创建WebApplicationContext Web应用的容器(最终继承自ApplicationContext), 并注册自定义的Web Config Class来提供配置
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // webApplicationContext.scan("main");
        // webApplicationContext.setConfigLocation("../beans.xml");
        context.register(MyApplicationContextConfig.class);

        /**
         *  ServletRegistration.Dynamic 这个接口可以通过servletContext方法添加servlet, 在后面可能要再配置configured
         */
        ServletRegistration.Dynamic registration = servletContext.addServlet("demo", new DispatcherServlet(context));
        // 加载默认的/resources/ApplicationContext.xml配置文件
        // servletContext.addListener(new ContextLoaderListener(context));

        // loadOnStartup – the initialization priority of the Servlet 参数为初始化的优先级
        registration.setLoadOnStartup(1);
        // 设置发送请求request mapping的URL格式(访问路径), 重写tomcat default homepage
        registration.addMapping("/");

        // registration.addMapping("*.html");
        // registration.addMapping("*.htm");
    }
}
