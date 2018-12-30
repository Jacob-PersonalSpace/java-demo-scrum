package com.demo.scrum.controller.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.demo.scrum.domain.User;
import com.demo.scrum.dto.response.APIResponse;
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
public class BrandControllerE2E {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private String bearerToken;

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
        APIResponse<?> actualResponseObject = objectMapper.readValue(tokenResponse.getResponse().getContentAsString(),
                APIResponse.class);

        setBearerToken("Bearer " + actualResponseObject.getData());
    }

    @Test
    public void createBrand() throws Exception {
        MvcResult result = this.mvc.perform(post("/brand/create").header("Authorization", getBearerToken())
                .param("customerID", "5c287af310e0fc2094fc62be").param("name", "aaaa")).andReturn();
        // MockHttpServletResponse response = result.getResponse();
        String s = result.getResponse().getContentAsString();
        System.out.println("----------------------------------" + s);
    }

    @Test
    public void getBrand() throws Exception {
        MvcResult result = this.mvc.perform(get("/brand/aaaa").header("Authorization", getBearerToken())).andReturn();
        // MockHttpServletResponse response = result.getResponse();
        String s = result.getResponse().getContentAsString();
        System.out.println("----------------------------------" + s);
    }

}
