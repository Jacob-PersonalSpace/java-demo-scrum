package com.demo.scrum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.demo.scrum.dto.request.SignupRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    public User(SignupRequest signupRequest) {
        name = signupRequest.getName();
        password = signupRequest.getPassword();
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true, length = 20)
    private String name;

    @NotNull
    @Column(length = 500)
    private String password;

    @Column(columnDefinition = "bit default 1")
    private Boolean active = true;

}