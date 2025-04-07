package rest_template;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplatePostEntity {

    private final RestTemplate restTemplate = new RestTemplate();

    // TODO. 直接获取ResponseEntity<Object>带有Status状态
    public void testPostForEntity() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-type", "application/json;charset=UTF-8");

        HttpEntity<String> request = new HttpEntity<>("jsonBody", headers);
        ResponseEntity<String> response = restTemplate.postForEntity("url", request, String.class);
    }
}
