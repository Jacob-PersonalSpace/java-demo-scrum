package com.demo.scrum.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1056308129814024956L;

    public UserNotFoundException(String name) {
        super("User " + name + " is not existend.");
    }
}