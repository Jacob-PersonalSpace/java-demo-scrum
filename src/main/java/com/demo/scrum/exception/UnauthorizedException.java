package com.demo.scrum.exception;

public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 9147917933122212070L;

    public UnauthorizedException() {
        super("Unauthorized.");
    }
}
