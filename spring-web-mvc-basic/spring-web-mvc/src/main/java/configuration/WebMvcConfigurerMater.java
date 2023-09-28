package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @EnableWebMvc的作用: 处理映射的功能
// 1. 确保IoC容器中必须包含ServletContext，否则会报错
// 2. 容器启动的时候，找容器中所有实现WebMvcConfigurer接口的类型，调用到指定的方法
// @Import({DelegatingWebMvcConfiguration.class})
//    @Autowired(required = false)
//    public void setConfigurers(List<WebMvcConfigurer> configurers) {
//        if (!CollectionUtils.isEmpty(configurers)) {
//            this.configurers.addWebMvcConfigurers(configurers);
//        }
//    }
@EnableWebMvc
// 配置扫描的package包，指定扫描特定的类型
@ComponentScan(basePackages = {"spring.web.mvc"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class})})
@Configuration
public class WebMvcConfigurerMater implements WebMvcConfigurer {

}
