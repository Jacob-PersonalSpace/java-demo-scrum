package com.demo.scrum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.demo.scrum.exception.PasswordNotMatchedException;
import com.demo.scrum.exception.ProjectNotFoundException;
import com.demo.scrum.exception.TaskNotFoundException;
import com.demo.scrum.exception.TaskStatusNotFoundException;
import com.demo.scrum.exception.UnauthorizedException;
import com.demo.scrum.exception.UserNotFoundException;
import com.demo.scrum.dto.response.APIResponse;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UserNotFoundException.class, PasswordNotMatchedException.class,
            ProjectNotFoundException.class, TaskStatusNotFoundException.class, TaskNotFoundException.class })
    @ResponseBody
    public ResponseEntity<?> badRequestHandler(HttpServletRequest req, Throwable e) {
        return ResponseEntity.badRequest()
                .body(new APIResponse<>(HttpStatus.BAD_REQUEST.value(), false, e.getMessage()));
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = { UnauthorizedException.class })
    @ResponseBody
    public APIResponse<String> unauthorizedHandler(HttpServletRequest req, Throwable e) {
        return new APIResponse<>(HttpStatus.UNAUTHORIZED.value(), false, e.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatus status,
            WebRequest webRequest) {
        return ResponseEntity.badRequest()
                .body(new APIResponse<>(HttpStatus.BAD_REQUEST.value(), false, e.getMessage()));
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new APIResponse<>(HttpStatus.BAD_REQUEST.value(), false, e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException e, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new APIResponse<>(HttpStatus.BAD_REQUEST.value(), false, buildMessages(e.getBindingResult())));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new APIResponse<>(HttpStatus.BAD_REQUEST.value(), false, buildMessages(e.getBindingResult())));
    }

    private String buildMessages(BindingResult result) {
        StringBuilder resultBuilder = new StringBuilder();
        List<ObjectError> errors = result.getAllErrors();

        if (errors != null && errors.size() > 0) {
            for (ObjectError error : errors) {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    String fieldName = fieldError.getField();
                    String fieldErrMsg = fieldError.getDefaultMessage();
                    resultBuilder.append(fieldName).append(" ").append(fieldErrMsg).append(";");
                }
            }
        }

        return resultBuilder.toString();
    }

}
