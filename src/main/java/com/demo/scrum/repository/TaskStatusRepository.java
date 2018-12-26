package com.demo.scrum.repository;

import com.demo.scrum.domain.TaskStatus;

import org.springframework.data.repository.CrudRepository;

public interface TaskStatusRepository extends CrudRepository<TaskStatus, Integer> {
}
