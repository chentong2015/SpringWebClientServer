package rest_template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

public class RestTemplatePost {

    // 请求失败的异常信息: "Product already exists"
    // org.springframework.web.client.HttpClientErrorException$BadRequest: 400
    public void testPostRequest() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-type", "application/json;charset=UTF-8");

        // Post Request body
        Product product = new Product("2", "mac");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(product);

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
        String url = "http://localhost:5679/products/2";

        // ResponseEntity<String> 获取返回Body类型的对象数据
        RestTemplate restTemplate = new RestTemplate();
        String responseBody = restTemplate.postForObject(url, request, String.class);
        System.out.println(responseBody);
    }

    class Product implements Serializable {

        private String id;
        private String name;

        public Product() {
        }

        public Product(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
