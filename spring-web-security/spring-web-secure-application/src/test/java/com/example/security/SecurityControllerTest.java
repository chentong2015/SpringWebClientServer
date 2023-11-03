package com.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "bookadmin", roles = {"USER"})
    void successIfSecurityApplies() throws Exception {
        mockMvc.perform(get("/library/books")
                        .param("genre", "Fiction")
                        .param("user", "user1")
                        .header("X-Application-Name", "Library"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("user1"))
                .andExpect(authenticated().withRoles("test"));
    }

    @Test
    @WithMockUser(username = "bookadmin", roles = {"ADMIN"})
    void failsForWrongAuthorization() throws Exception {
        mockMvc.perform(get("/library/books")
                        .param("genre", "Fiction")
                        .param("user", "nobody")
                        .header("X-Application-Name", "Library"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void failsIfSecurityApplies() throws Exception {
        mockMvc.perform(get("/library/books")
                        .param("genre", "Fiction")
                        .param("user", "bookadmin")
                        .header("X-Application-Name", "Library"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = "bookadmin", userDetailsServiceBeanName = "userDetailsService")
    void testBookWithConfiguredUserDetails() throws Exception {
        mockMvc.perform(get("/library/books")
                        .param("genre", "Fantasy")
                        .param("user", "bookadmin")
                        .header("X-Application-Name", "Library"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithUserDetails(value = "bookadmin", userDetailsServiceBeanName = "userDetailsService")
    void failsIfMandatoryHeaderIsMissing() throws Exception {
        mockMvc.perform(get("/library/books")
                        .param("genre", "Fantasy")
                        .param("user", "bookadmin"))
                //.header("X-Application-Name", "Library"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "bookadmin", userDetailsServiceBeanName = "userDetailsService")
    void failsIfPreAuthorizeConditionFails() throws Exception {
        mockMvc.perform(get("/library/books")
                        .param("genre", "Fantasy")
                        .param("user", "bookuser")
                        .header("X-Application-Name", "Library"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    // @WithUserDetails(value="bookadmin", userDetailsServiceBeanName="userDetailsService")
    @Test
    void testBookWithWrongCredentialsUserDetails() throws Exception {
        mockMvc.perform(get("/library/books")
                        .param("genre", "Fantasy")
                        .param("user", "bookadmin")
                        .header("X-Application-Name", "Library")
                        .with(httpBasic("bookadmin", "password")))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
