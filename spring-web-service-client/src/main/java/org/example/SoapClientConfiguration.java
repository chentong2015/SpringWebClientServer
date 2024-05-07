package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.ClientHttpRequestMessageSender;

@Configuration
public class SoapClientConfiguration extends AbstractClientConfiguration {

    @Value("${client.soap.default-uri:}")
    private String defaultUri;

    // TODO. SOAP API请求，配置xml请求数据的Jaxb Marshaller
    @Bean
    public WebServiceTemplate webServiceTemplate(WebServiceTemplateBuilder webServiceTemplateBuilder,
                                                 Jaxb2Marshaller jaxb2Marshaller) throws Exception {
        WebServiceTemplate webServiceTemplate = webServiceTemplateBuilder.build();
        if (StringUtils.hasText(this.defaultUri)) {
            webServiceTemplate.setDefaultUri(this.defaultUri);
        }

        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setMessageSender(new ClientHttpRequestMessageSender(this.createRequestFactory()));
        return webServiceTemplate;
    }
}
