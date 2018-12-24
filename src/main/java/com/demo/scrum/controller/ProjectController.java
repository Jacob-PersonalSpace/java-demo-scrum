package com.demo.scrum.controller;

import com.demo.scrum.domain.Project;
import com.demo.scrum.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/create")
    public Project create(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String description) {
        return projectService.create(name, description);
    }
}
