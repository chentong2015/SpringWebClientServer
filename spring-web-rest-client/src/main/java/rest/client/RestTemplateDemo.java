package rest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

// TODO. Spring RestTemplate 请求的客户端
// - 推荐使用RestTemplate/AsyncRestTemplate发送请求
// - 客户端RestTemplate提供Request请求基本操作
public class RestTemplateDemo {

    public static void testGetRequest() {
        String url = "http://localhost:8080/v1/base";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }

    // 请求失败的异常信息: "Product already exists"
    // org.springframework.web.client.HttpClientErrorException$BadRequest: 400
    public static void testPostRequest() throws JsonProcessingException {
        // Post Request Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-type", "application/json;charset=UTF-8");

        // Post Request body
        Product product = new Product("2", "mac");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(product);

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        String url = "http://localhost:5679/products/2";
        RestTemplate restTemplate = new RestTemplate();

        // ResponseEntity<String> 获取返回Body类型的对象数据
        String responseBody = restTemplate.postForObject(url, request, String.class);
        System.out.println(responseBody);
    }
}
