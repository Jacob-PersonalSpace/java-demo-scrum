package com.demo.scrum.controller;

import javax.servlet.http.HttpServletRequest;

import com.demo.scrum.exception.PasswordNotMatchedException;
import com.demo.scrum.exception.ProjectNotFoundException;
import com.demo.scrum.exception.TaskStatusNotFoundException;
import com.demo.scrum.exception.UnauthorizedException;
import com.demo.scrum.exception.UserNotFoundException;
import com.demo.scrum.viewObject.APIResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { UserNotFoundException.class, PasswordNotMatchedException.class,
            ProjectNotFoundException.class, TaskStatusNotFoundException.class })
    @ResponseBody
    public APIResponse<String> badRequestHandler(HttpServletRequest req, Throwable e) {
        return new APIResponse<>(HttpStatus.BAD_REQUEST.value(), false, e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = { UnauthorizedException.class })
    @ResponseBody
    public APIResponse<String> unauthorizedHandler(HttpServletRequest req, Throwable e) {
        return new APIResponse<>(HttpStatus.UNAUTHORIZED.value(), false, e.getMessage());
    }

}
