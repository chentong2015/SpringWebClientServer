package rest_template;

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
}
