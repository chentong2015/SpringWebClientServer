package org.example;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class AbstractClientConfiguration {

    // 配置Spring RestTemplate使用的HttpClient客户端
    protected final ClientHttpRequestFactory createRequestFactory() throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectTimeout(100);
        return requestFactory;
    }
}
