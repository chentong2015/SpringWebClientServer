package rest_template;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringRestTemplateMockTest {

    @Autowired
    private MockMvc mockMvc;

    // TODO. Mock注入到SpringContext中的RestTemplate Bean
    @MockitoBean
    private RestTemplate restTemplate;

    public void testEndpoints() throws Exception {
        // Mock客户端POST方法的情况结果
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any()))
                .thenThrow(new RuntimeException("Internal Error"));

        mockMvc.perform(post("/v1/user/")
                        .content("body".getBytes())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("400 Product already exists"));
    }
}
