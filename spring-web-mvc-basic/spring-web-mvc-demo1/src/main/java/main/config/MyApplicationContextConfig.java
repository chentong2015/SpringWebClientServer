package main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @EnableWebMvc: use configuration class to import Spring MVC Configuration    特别针对Spring MVC要添加的注解,使用配置 !!
 * 1. Allows registers and beans (views resolver, request mappers) to spring MVC 允许特定的bean注册者注入Container
 * .
 * WebMvcConfigurer: this interface defines callback methods to customize configuration for spring MVC
 * 1. 可以通过重写一些基本的方法，来自定义配置
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "main")
public class MyApplicationContextConfig implements WebMvcConfigurer {

    /**
     * 针对于Spring Web Application Context容器的配置               ===> 在web.xml中配置<context-param>  !!!
     * 1. WebApplicationContext: Spring Web App Container 针对于Web App的容器
     * 2. Contains some beans are registered, DispatcherServlet delegates to some beans !!!
     */

    /**
     * InternalResourceViewResolver extends UrlBasedViewResolver  ===> 在web.xml中配置注入InternalResourceViewResolver
     * 1. Map between views names and views
     * 2. Enable and use "JSTL views" for rendering the content
     */
    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Configure automated controllers that pre-configure with a response status code or views to render the response body
     * Configure the path to the home views, spring use the views without a com.ctong.springboot.controller
     * 直接定义web application启动时的界面，而不需要借助控制器
     */
    /* @registry 用来注册一个预先配置的automated com.ctong.springboot.controller, for home page ====> 取消默认的home页面 webapp/index.html */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // localhost:8080/
        // /WEB-INF/views/home.jsp
        registry.addViewController("/").setViewName("home");
    }
}
