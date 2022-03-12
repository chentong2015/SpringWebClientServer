package com.ctong.main.config;

import org.springframework.core.Ordered;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebMvcInitializer implements WebApplicationInitializer, Ordered {

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        System.out.println("start application ...");

        // 使用Annotation(纯java)注解的方式来配置Application Context, 使用注解@Configuration的类型
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);
        DispatcherServlet servlet = new DispatcherServlet(context);

        ServletRegistration.Dynamic registration = servletContext.addServlet("name", servlet);
        registration.addMapping("/");
        registration.setLoadOnStartup(1);
    }
    
    @Override
    public int getOrder() {
        return 10;
    }
}
