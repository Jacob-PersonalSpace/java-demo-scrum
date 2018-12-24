package com.demo.scrum.controller;

import com.demo.scrum.domain.Task;
import com.demo.scrum.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/create")
    public Task create(@RequestParam(value = "project", required = true) Integer project,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "assigner", required = false) Integer assigner,
            @RequestParam(value = "reporter", required = false) Integer reporter,
            @RequestParam(value = "status", defaultValue = "1") Integer status) {
        return taskService.create(project, name, description, status, assigner, reporter);
    }
}
