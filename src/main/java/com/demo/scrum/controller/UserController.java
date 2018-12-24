package com.demo.scrum.controller;

import java.util.List;

import com.demo.scrum.domain.User;
import com.demo.scrum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup")
    public User signup(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(value = "/signin")
    public String signin(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password) {
        return userService.generateToken(name, password);
    }

    @GetMapping(value = "/refreshToken")
    public String refreshToken() {
        return userService.refreshToken();
    }

    @GetMapping(value = "/users")
    public List<User> getAllActiveUsers() {
        return userService.getAllActiveUsers();
    }
}
