package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.ClientHttpRequestMessageSender;

@Configuration
public class SoapClientConfiguration extends AbstractClientConfig {

    @Value("${client.soap.default-uri:}")
    private String defaultUri;

    @Bean
    public WebServiceTemplate webServiceTemplate(WebServiceTemplateBuilder webServiceTemplateBuilder, Jaxb2Marshaller jaxb2Marshaller) throws Exception {
        WebServiceTemplate webServiceTemplate = webServiceTemplateBuilder.build();
        if (StringUtils.hasText(this.defaultUri)) {
            webServiceTemplate.setDefaultUri(this.defaultUri);
        }

        // TODO. SOAP请求，配置xml请求数据的Jaxb Marshaller
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);

        // TODO. WebServiceTemplateApache HttpClient客户端发送请求
        ClientHttpRequestMessageSender requestMessageSender =
                new ClientHttpRequestMessageSender(this.createRequestFactory());
        webServiceTemplate.setMessageSender(requestMessageSender);
        return webServiceTemplate;
    }
}
