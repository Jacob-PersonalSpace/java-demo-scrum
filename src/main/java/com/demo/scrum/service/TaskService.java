package com.demo.scrum.service;

import java.util.List;
import java.util.Optional;

import com.demo.scrum.domain.Project;
import com.demo.scrum.domain.Task;
import com.demo.scrum.domain.TaskStatus;
import com.demo.scrum.domain.User;
import com.demo.scrum.exception.ProjectNotFoundException;
import com.demo.scrum.exception.TaskStatusNotFoundException;
import com.demo.scrum.exception.UserNotFoundException;
import com.demo.scrum.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private UserService userService;

    public Task create(Integer projectID, String name, String description, Integer statusID, Integer assignerID,
            Integer reporterID) {
        Optional<Project> targetProject = projectService.findOne(projectID);
        User assigner = null;
        User reporter = null;
        Optional<TaskStatus> targetTaskStatus = taskStatusService.findOne(statusID);

        if (assignerID != null && assignerID > 0) {
            Optional<User> targetAssigner = userService.get(assignerID);

            if (!targetAssigner.isPresent()) {
                throw new UserNotFoundException("Assigner " + assignerID + " is not existend.");
            } else {
                assigner = targetAssigner.get();
            }
        }

        if (reporterID != null && reporterID > 0) {
            Optional<User> targetReporter = userService.get(reporterID);

            if (!targetReporter.isPresent()) {
                throw new UserNotFoundException("Reporter " + reporterID + " is not existend.");
            } else {
                reporter = targetReporter.get();
            }
        }

        if (!targetProject.isPresent()) {
            throw new ProjectNotFoundException("Project " + projectID + " is not existend.");
        }

        if (!targetTaskStatus.isPresent()) {
            throw new TaskStatusNotFoundException("Project " + statusID + " is not existend.");
        }

        Task task = new Task(targetProject.get(), name, description, targetTaskStatus.get(), assigner, reporter);
        return taskRepository.save(task);
    }

    public List<Task> findAllByProjectID(Integer projectID) {
        return taskRepository.findByProject_Id(projectID);
    }

    public Task findOneByProjectIDAndTaskID(Integer projectID, Integer taskID) {
        return taskRepository.findByIdAndProject_Id(taskID, projectID);
    }
}
