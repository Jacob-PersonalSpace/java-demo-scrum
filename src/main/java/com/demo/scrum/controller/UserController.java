package com.demo.scrum.controller;

import java.util.List;

import com.demo.scrum.domain.User;
import com.demo.scrum.viewObject.APIResponse;
import com.demo.scrum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public APIResponse<User> signup(@RequestBody User user) {
        User newUser = userService.create(user);
        return new APIResponse<>(HttpStatus.OK.value(), true, newUser);
    }

    @GetMapping(value = "/signin")
    public APIResponse<String> signin(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password) {
        String token = userService.generateToken(name, password);
        return new APIResponse<>(HttpStatus.OK.value(), true, token);
    }

    @GetMapping(value = "/refreshToken")
    public APIResponse<String> refreshToken() {
        String newToken = userService.refreshToken();
        return new APIResponse<>(HttpStatus.OK.value(), true, newToken);
    }

    @GetMapping(value = "/users")
    public APIResponse<List<User>> getAllActiveUsers() {
        List<User> users = userService.getAllActiveUsers();
        return new APIResponse<>(HttpStatus.OK.value(), true, users);
    }

}
