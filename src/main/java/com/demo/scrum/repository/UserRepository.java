package com.demo.scrum.repository;

import com.demo.scrum.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}