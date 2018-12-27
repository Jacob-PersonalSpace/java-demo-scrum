package com.demo.scrum.exception;

public class TaskStatusNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public TaskStatusNotFoundException(String name) {
        super("Task status " + name + " is not existend.");
    }
}
