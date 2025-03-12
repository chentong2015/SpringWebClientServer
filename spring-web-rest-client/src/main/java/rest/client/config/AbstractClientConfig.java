package rest.client.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class AbstractClientConfig {

    protected final ClientHttpRequestFactory createRequestFactory() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectTimeout(100);
        return requestFactory;
    }
}
