package com.demo.scrum.service;

import java.util.Optional;

import com.demo.scrum.domain.TaskStatus;
import com.demo.scrum.repository.TaskStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusService {
    @Autowired
    private TaskStatusRepository taskStatusRepository;

    public Optional<TaskStatus> findOne(Integer id) {
        return taskStatusRepository.findById(id);
    }
}