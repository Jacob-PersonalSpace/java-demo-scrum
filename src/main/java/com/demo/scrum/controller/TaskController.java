package com.demo.scrum.controller;

import java.util.List;

import javax.validation.Valid;

import com.demo.scrum.domain.Task;
import com.demo.scrum.service.TaskService;
import com.demo.scrum.dto.response.APIResponse;
import com.demo.scrum.dto.ViewTask;
import com.demo.scrum.dto.request.GetTasksByProjectIDRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public APIResponse<Task> create(@RequestBody ViewTask viewTask) {
        Task newTask = taskService.create(viewTask);
        return new APIResponse<>(HttpStatus.OK.value(), true, newTask);
    }

    @GetMapping(value = "/tasks/{projectID}")
    public ResponseEntity<?> getTasksByProjectID(@Valid GetTasksByProjectIDRequest getTasksByProjectIDRequest) {
        List<Task> tasks = taskService.findAllByProjectID(getTasksByProjectIDRequest.getProductID());
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), true, tasks));
    }

    @GetMapping(value = "/{taskID}")
    public APIResponse<ViewTask> getTask(@PathVariable("taskID") Integer taskID,
            @RequestParam(value = "name", required = true) String name) {
        ViewTask result = taskService.get(taskID, name);
        return new APIResponse<>(HttpStatus.OK.value(), true, result);
    }

}
