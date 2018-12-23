package com.demo.scrum.service;

import com.demo.scrum.domain.Project;
import com.demo.scrum.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project create(String name, String description) {
        Project newProject = new Project(name, description);

        return projectRepository.save(newProject);
    }
}