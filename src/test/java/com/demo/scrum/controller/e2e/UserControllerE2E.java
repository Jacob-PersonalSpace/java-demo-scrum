package com.demo.scrum.controller.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.demo.scrum.domain.User;
import com.demo.scrum.viewObject.APIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerE2E {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private String bearerToken;

    @Before
    public void insertUserToDBAndGenerateTokenBefore() throws Exception {
        User requestBodyObject = new User("jacob", "haha");
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestBodyJSON = ow.writeValueAsString(requestBodyObject);

        this.mvc.perform(post("/user/signup").contentType(MediaType.APPLICATION_JSON).content(requestBodyJSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        MvcResult tokenResponse = this.mvc.perform(get("/user/signin").param("name", "jacob").param("password", "haha"))
                .andReturn();
        APIResponse<String> actualResponseObject = objectMapper
                .readValue(tokenResponse.getResponse().getContentAsString(), APIResponse.class);

        setBearerToken("Bearer " + actualResponseObject.getData());
    }

    @Test
    public void refreshToken() throws Exception {
        this.mvc.perform(get("/user/refreshToken").header("Authorization", getBearerToken()));
    }

    /**
     * @return the bearerToken
     */
    public String getBearerToken() {
        return bearerToken;
    }

    /**
     * @param bearerToken the bearerToken to set
     */
    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    @Test
    public void signup() throws Exception {
        User requestBodyObject = new User("name1", "password1");
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestBodyJSON = ow.writeValueAsString(requestBodyObject);

        this.mvc.perform(post("/user/signup").contentType(MediaType.APPLICATION_JSON).content(requestBodyJSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void signin() throws Exception {
        String name = "jacob";
        String password = "haha";

        this.mvc.perform(get("/user/signin").param("name", name).param("password", password));
    }

}