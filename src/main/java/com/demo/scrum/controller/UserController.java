package com.demo.scrum.controller;

import com.demo.scrum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Integer signup(@RequestParam String name, @RequestParam String password) {
        return userService.create(name, password);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }
}