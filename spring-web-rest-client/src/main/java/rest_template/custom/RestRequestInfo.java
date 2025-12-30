package rest_template.custom;

import org.springframework.http.HttpMethod;

// 封装有关请求的相关信息
public class RestRequestInfo {

    String requestId;
    String requestUrl;
    Object requestBody;
    HttpMethod requestMethod;

    // TODO. 重试次数需要在请求中使用
    int retryCount;
    public static final int DEFAULT_RETRY_COUNT = 3;

    public RestRequestInfo() {
        this.retryCount = 3;
    }

    public RestRequestInfo(String requestUrl) {
        this(requestUrl, (Object)null, HttpMethod.GET);
    }

    public RestRequestInfo(String requestUrl, Object requestBody) {
        this(requestUrl, requestBody, HttpMethod.POST);
    }

    public RestRequestInfo(String requestUrl, Object requestBody, HttpMethod method) {
        this.retryCount = 3;
        this.setRequestUrl(requestUrl);
        this.setRequestBody(requestBody);
        this.setRequestMethod(method);
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public RestRequestInfo setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    public Object getRequestBody() {
        return this.requestBody;
    }

    public RestRequestInfo setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public HttpMethod getRequestMethod() {
        return this.requestMethod;
    }

    public RestRequestInfo setRequestMethod(HttpMethod requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public RestRequestInfo setRetryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public RestRequestInfo setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }
}
