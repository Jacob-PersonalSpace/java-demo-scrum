package com.demo.scrum.exception;

public class ProjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProjectNotFoundException(Integer projectID) {
        super("Project " + projectID + " is not existend.");
    }
}