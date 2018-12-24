package com.demo.scrum.repository;

import java.util.List;

import com.demo.scrum.domain.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByProject_Id(Integer projectID);
    Task findByIdAndProject_Id(Integer taskID, Integer projectID);
}
