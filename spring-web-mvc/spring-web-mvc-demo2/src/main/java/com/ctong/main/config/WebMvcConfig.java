package com.ctong.main.config;

import com.ctong.main.interceptors.MyWebRequestInterceptor;
import com.ctong.main.interceptors.VisitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration // spring容器的配置，替代bean.xml配置文件
@EnableWebMvc  // 开启MVC配置和功能
@ComponentScan("com.ctong.main")
public class WebMvcConfig implements WebMvcConfigurer {

    // 路径的解析：convert views name to full url path
    // urlBasedViewResolver能够支持的view technologies页面技术更多, 支持所有实现了AbstractUrlBasedView抽象类型的类型
    // InternalResourceViewResolver extends UrlBasedViewResolver, 只能使用指定类型的view技术
    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        // JstlView extends InternalResourceView extends AbstractUrlBasedView
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    /**
     * HandlerMapping: 在查找具体的@RequestMapping时，是否使用全路径或匹配后缀 /
     * .setUseTrailingSlashMatch(true) 默认为true: /index和/index/是相同的
     * .setAlwaysUseFullPath(true)     默认为true
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping mappingHandlerMapping = new RequestMappingHandlerMapping();
        mappingHandlerMapping.setUseTrailingSlashMatch(true);
        mappingHandlerMapping.setAlwaysUseFullPath(true);
        return mappingHandlerMapping;
    }

    // 提供默认的控制器和渲染的VIEW页面
    // addViewControllers(): pre-configured automated controllers
    // View Controller Registry: registers the parameters of view com.ctong.springboot.controller that selects a view for rendering
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // <mvc:view-com.ctong.springboot.controller path="/" view-name="home" />
        registry.addViewController("/").setViewName("home");
    }

    // Serve static resources files, such as CSS file, images 指定使用静态资源的路径
    // Path pattern: ** 匹配目录下面所有层级的路径文件
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/css/");
    }

    // 注册VisitorInterceptor拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitorInterceptor());
        registry.addWebRequestInterceptor(new MyWebRequestInterceptor());
    }
}
