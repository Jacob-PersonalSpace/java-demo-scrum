package com.demo.scrum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.demo.scrum.domain.User;
import com.demo.scrum.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void signup() throws Exception {
        User requestBodyObject = new User("name1", "password1");
        User expectResponseObject = new User("name2", "password2");
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestBodyJSON = ow.writeValueAsString(requestBodyObject);

        // given(this.userService.create(any())).willReturn(expectResponseObject);

        MvcResult actualResponse = this.mvc
                .perform(post("/signup").contentType(MediaType.APPLICATION_JSON).content(requestBodyJSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        User actualResponseObject = objectMapper.readValue(actualResponse.getResponse().getContentAsString(),
                User.class);
        assertEquals(ow.writeValueAsString(expectResponseObject), ow.writeValueAsString(actualResponseObject));
    }

    @Test
    public void signin() throws Exception {
        String name = "name1";
        String password = "password1";
        String expectedReturn = "expectedReturn";
        given(this.userService.generateToken(name, password)).willReturn(expectedReturn);

        this.mvc.perform(get("/user/signin").param("name", name).param("password", password))
                .andExpect(status().isOk());
    }

    @Test
    public void refreshToken() throws Exception {
        given(this.userService.refreshToken()).willReturn("hahaha");

        this.mvc.perform(get("/refreshToken")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("hahaha"));
    }

    @Test
    public void home() throws Exception {
        this.mvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

}