package rest_template.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

// 封装请求结果的相关信息
public class RestResponseInfo<T> implements Serializable {

    private static final long serialVersionUID = -8655793489572515974L;

    private HttpStatus httpStatus;
    private int httpCode;
    private String httpMessage;
    private String message;
    private Long count;
    private Long totalCount;
    private Long startIndex;
    private Long endIndex;
    private Integer errorCount;
    private List<RestError> errors;
    private Object data;
    private URI createdUri;

    public RestResponseInfo() {
        this.errorCount = 0;
    }

    public RestResponseInfo(HttpStatus status) {
        this(status, (String)null);
    }

    public RestResponseInfo(int status) {
        this(HttpStatus.valueOf(status));
    }

    public RestResponseInfo(HttpStatus status, String message) {
        this.errorCount = 0;
        this.setHttpStatus(status);
        this.setMessage(message);
    }

    public RestResponseInfo(HttpStatus status, RestError error) {
        this.errorCount = 0;
        this.setHttpStatus(status);
        this.addError(error);
    }

    public int getHttpCode() {
        return this.httpCode;
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public RestResponseInfo<T> setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.httpCode = httpStatus.value();
        this.httpMessage = httpStatus.getReasonPhrase();
        return this;
    }

    public String getHttpMessage() {
        return this.httpMessage;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getMessage() {
        return this.message;
    }

    public RestResponseInfo<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Long getCount() {
        return this.count != null ? this.count : 0L;
    }

    public RestResponseInfo<T> setCreatedUri(String createdUri) {
        try {
            this.createdUri = new URI(createdUri);
        } catch (URISyntaxException var3) {
        }
        return this;
    }

    @JsonIgnore
    public URI getCreatedUri() {
        return this.createdUri;
    }

    public RestResponseInfo<T> setCount(Long count) {
        this.count = count;
        return this;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Long getTotalCount() {
        return this.totalCount;
    }

    public RestResponseInfo<T> setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Long getStartIndex() {
        return this.startIndex;
    }

    public RestResponseInfo<T> setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Long getEndIndex() {
        return this.endIndex;
    }

    public RestResponseInfo<T> setEndIndex(Long endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Integer getErrorCount() {
        return this.errorCount;
    }

    public RestResponseInfo<T> setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
        return this;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<RestError> getErrors() {
        return this.errors;
    }

    @JsonIgnore
    public boolean hasErrors() {
        return this.getHttpCode() > 400 || this.errors != null && this.errors.size() > 0;
    }

    public RestResponseInfo<T> setErrors(List<RestError> errors) {
        this.errors = errors;
        return this;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object getData() {
        return this.errors != null && !this.errors.isEmpty() ? null : this.data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.getHttpCode() < 400;
    }

    public RestResponseInfo<T> addError(int errorCode, String errorMessage, String devMessage, String moreInfo) {
        RestError err = new RestError(errorCode, errorMessage);
        err.setDeveloperMessage(devMessage);
        err.setMoreInfoURL(moreInfo);
        this.addError(err);
        return this;
    }

    public RestResponseInfo<T> addError(int errorCode, String errorMessage) {
        this.addError(errorCode, errorMessage, (String)null, (String)null);
        return this;
    }

    public RestResponseInfo<T> addError(int errorCode, String errorMessage, String devMessage) {
        this.addError(errorCode, errorMessage, devMessage, (String)null);
        return this;
    }

    public RestResponseInfo<T> addError(RestError error) {
        if (this.errors == null) {
            this.errors = new ArrayList();
        }

        this.errors.add(error);
        Integer var2 = this.errorCount;
        this.errorCount = this.errorCount + 1;
        return this;
    }
}
