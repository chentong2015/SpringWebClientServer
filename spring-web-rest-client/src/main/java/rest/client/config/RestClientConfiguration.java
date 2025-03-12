package rest.client.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfiguration extends AbstractClientConfig {

    // TODO. RestTemplate基于Apache HttpClient客户端发送请求
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setRequestFactory(this.createRequestFactory());
        return restTemplate;
    }
}
