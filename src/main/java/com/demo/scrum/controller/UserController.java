package com.demo.scrum.controller;

import com.demo.scrum.domain.User;
import com.demo.scrum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public User signup(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping(value = "/signin")
    public String signin(@RequestBody User user) {
        return userService.generateToken(user);
    }
    
    @ApiOperation(value="展示首页信息", notes = "展示首页信息")
    @GetMapping("/")
    public String home() {
        return "Hello World!";
    }
}