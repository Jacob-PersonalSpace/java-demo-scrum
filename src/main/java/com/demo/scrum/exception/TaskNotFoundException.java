package com.demo.scrum.exception;

public class TaskNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(String name) {
        super("Task " + name + " is not existend.");
    }
}
