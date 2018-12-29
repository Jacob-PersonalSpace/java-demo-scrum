package com.demo.scrum.repository;

import java.util.List;

import com.demo.scrum.domain.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select a from User a where a.name = ?1")
    User findOne(String name);

    @Query("select a from User a where a.id = ?1")
    User findOne(Integer id);

    @Query("select new User(a.id, a.name) from User a where a.active = 1")
    List<User> findAllActive();

    @Transactional
    @Modifying
    @Query("update User set password = :password")
    int updatePassword(@Param(value = "password") String password);

}
