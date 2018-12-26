package com.demo.scrum.exception;

public class PasswordNotMatchedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PasswordNotMatchedException() {
        super("Password does not match.");
    }
}