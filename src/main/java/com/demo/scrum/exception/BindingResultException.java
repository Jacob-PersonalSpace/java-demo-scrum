package com.demo.scrum.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class BindingResultException extends BindException {
    private static final long serialVersionUID = 1L;

    public BindingResultException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
