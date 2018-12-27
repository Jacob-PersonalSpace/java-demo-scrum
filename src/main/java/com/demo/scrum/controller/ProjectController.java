package com.demo.scrum.controller;

import com.demo.scrum.domain.Project;
import com.demo.scrum.service.ProjectService;
import com.demo.scrum.dto.response.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/create")
    public APIResponse<Project> create(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String description) {
        Project newProject = projectService.create(name, description);
        return new APIResponse<Project>(HttpStatus.OK.value(), true, newProject);
    }
}
