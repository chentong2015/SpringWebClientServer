package rest_template.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

// TODO. RestTemplate的封装类型: 注意代码质量, 方法重载等
public class RestTemplateWrapper {

    private final Logger LOGGER;
    private final String serviceName;
    private final String serviceBasePath;

    private final boolean sslEnabled;
    private boolean throwWhenUnavailable;
    private boolean throwOnError;

    private final RestTemplate restTemplate;

    public RestTemplateWrapper(String serviceName, RestTemplate restTemplate) {
        this(serviceName, restTemplate, serviceName, true);
    }

    public RestTemplateWrapper(String serviceName, RestTemplate restTemplate, String serviceBasePath, boolean sslEnabled) {
        this.LOGGER = LoggerFactory.getLogger(RestTemplateWrapper.class);
        this.throwWhenUnavailable = true;
        this.throwOnError = false;
        this.serviceName = serviceName;
        this.restTemplate = restTemplate;
        this.serviceBasePath = serviceBasePath;
        this.sslEnabled = sslEnabled;
    }

    private HttpHeaders useDefaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

    public <T> RestResponseInfo<T> query(RestRequestInfo requestInfo) {
        return this.query(requestInfo, useDefaultHttpHeaders());
    }

    public <T> ResponseEntity<T> query(RestRequestInfo requestInfo, ParameterizedTypeReference<T> parameterizedTypeReference) {
        return this.queryInternal(requestInfo, parameterizedTypeReference, useDefaultHttpHeaders());
    }

    private <T> RestResponseInfo<T> query(RestRequestInfo requestInfo, HttpHeaders headers) {
        ResponseEntity<RestResponseInfo<T>> responseEntity = this.queryInternal(requestInfo, new ParameterizedTypeReference<>() {}, headers);
        if (null != responseEntity) {
            RestResponseInfo body = responseEntity.getBody();
            if (body == null) {
                return new RestResponseInfo(responseEntity.getStatusCode().value());
            } else {
                body.setHttpStatus(HttpStatus.valueOf(responseEntity.getStatusCode().value()));
                return body;
            }
        } else {
            return new RestResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // HttpEntity包含请求的Header+RequestBody相关信息
    private <T> ResponseEntity<T> queryInternal(RestRequestInfo requestInfo, ParameterizedTypeReference<T> parameterizedTypeReference, HttpHeaders headers) {
        String url = parserRequestUrl(requestInfo);
        HttpEntity httpEntity = new HttpEntity(headers);
        if (requestInfo.getRequestBody() != null) {
            httpEntity = new HttpEntity(requestInfo.getRequestBody(), headers);
        }

        // TODO. RestTemplate Exchange发送Http请求, 使用remainingAttempts重试次数!
        try {
            return this.restTemplate.exchange(url, requestInfo.getRequestMethod(), httpEntity, parameterizedTypeReference);
        } catch (Exception e) {
            return handleQueryException(url, e);
        }
    }

    private String parserRequestUrl(RestRequestInfo requestInfo) {
        String requestUrl = requestInfo.getRequestUrl();
        requestUrl = requestUrl.startsWith("/") ? requestUrl : "/" + requestUrl;
        String protocol = this.sslEnabled ? "https://" : "http://";
        if (StringUtils.isEmpty(this.serviceBasePath)) {
            return protocol + this.serviceName + requestUrl;
        } else {
            return protocol + this.serviceName + "/" + this.serviceBasePath + requestUrl;
        }
    }

    private <T> ResponseEntity<T> handleQueryException(String url, Exception e) {
        if (this.isThrowOnError()) {
            throw new RuntimeException();
        } else {
            this.LOGGER.error("Exception calling url '" + url + "': " + e.getMessage());
            this.LOGGER.debug("", e);
            if (e instanceof HttpStatusCodeException) {
                HttpStatusCodeException exception = (HttpStatusCodeException)e;
                this.LOGGER.error("'" + url + "' return error code " + exception.getStatusCode());
                return new ResponseEntity(exception.getStatusCode());
            } else {
                return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public RestTemplateWrapper setThrowWhenUnavailable(boolean throwWhenUnavailable) {
        this.throwWhenUnavailable = throwWhenUnavailable;
        return this;
    }

    public boolean isThrowWhenUnavailable() {
        return this.throwWhenUnavailable;
    }

    public void setThrowOnError(boolean throwOnError) {
        this.throwOnError = throwOnError;
    }

    public boolean isThrowOnError() {
        return this.throwOnError;
    }
}
