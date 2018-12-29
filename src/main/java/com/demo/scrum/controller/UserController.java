package com.demo.scrum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.demo.scrum.domain.User;
import com.demo.scrum.dto.response.APIResponse;
import com.demo.scrum.dto.request.SignupRequest;
import com.demo.scrum.dto.response.SignupResponse;
import com.demo.scrum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest signupRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMsg = new ArrayList<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                errorMsg.add(fieldError.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(new APIResponse<>(HttpStatus.BAD_REQUEST.value(), false, errorMsg));
        }

        SignupResponse responseData = userService.create(signupRequest);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), true, responseData));
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

    @PostMapping(value = "/update")
    public ResponseEntity<?> updatePassword(@RequestParam String password) {
        userService.update(password);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), true));
    }

}
