package com.demo.scrum.service;

import java.util.List;
import java.util.Optional;

import com.demo.scrum.domain.Project;
import com.demo.scrum.domain.Task;
import com.demo.scrum.domain.TaskStatus;
import com.demo.scrum.domain.User;
import com.demo.scrum.exception.ProjectNotFoundException;
import com.demo.scrum.exception.TaskNotFoundException;
import com.demo.scrum.exception.TaskStatusNotFoundException;
import com.demo.scrum.exception.UserNotFoundException;
import com.demo.scrum.repository.TaskRepository;
import com.demo.scrum.dto.ViewTask;
import com.demo.scrum.dto.request.GetTasksByTaskIDRequest;
import com.demo.scrum.dto.response.GetTaskByTaskIDResponse;

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

    public Task create(ViewTask viewTask) {
        Optional<Project> targetProject = projectService.findOne(viewTask.getProjectID());
        User assigner = null;
        User reporter = null;
        Optional<TaskStatus> targetTaskStatus = taskStatusService.findOne(viewTask.getStatusID());

        if (viewTask.getAssignerID() != null && viewTask.getAssignerID() > 0) {
            Optional<User> DBAssigner = userService.get(viewTask.getAssignerID());

            if (!DBAssigner.isPresent()) {
                throw new UserNotFoundException(viewTask.getAssignerName());
            } else {
                assigner = DBAssigner.get();
            }
        }

        if (viewTask.getReporterID() != null && viewTask.getReporterID() > 0) {
            Optional<User> DBReporter = userService.get(viewTask.getReporterID());

            if (!DBReporter.isPresent()) {
                throw new UserNotFoundException(viewTask.getReporterName());
            } else {
                reporter = DBReporter.get();
            }
        }

        if (!targetProject.isPresent()) {
            throw new ProjectNotFoundException(viewTask.getProjectName());
        }

        if (!targetTaskStatus.isPresent()) {
            throw new TaskStatusNotFoundException(viewTask.getStatusName());
        }

        Task task = new Task(targetProject.get(), viewTask.getName(), viewTask.getDescription(), targetTaskStatus.get(),
                assigner, reporter);
        return taskRepository.save(task);
    }

    public List<Task> findAllByProjectID(Integer projectID) {
        return taskRepository.findByProject_Id(projectID);
    }

    public GetTaskByTaskIDResponse get(GetTasksByTaskIDRequest getTasksByTaskIDRequest) {
        Task task = taskRepository.findById(getTasksByTaskIDRequest.getTaskID())
                .orElseThrow(() -> new TaskNotFoundException(getTasksByTaskIDRequest.getName()));

        return new GetTaskByTaskIDResponse(task);
    }

}
