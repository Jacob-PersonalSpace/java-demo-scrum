package com.demo.scrum.repository;

import com.demo.scrum.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select a from User a where a.name = ?1 and a.password = ?2")
    User findOne(String name, String password);

    @Query("select a from User a where a.name = ?1")
    User findByName(String name);
}