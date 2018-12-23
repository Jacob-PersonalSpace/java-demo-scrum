package com.demo.scrum.domain;

import javax.persistence.*;

@Entity
@Table(name = "taskStatus")
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String name;

    @Column(columnDefinition = "bit default 1")
    private Boolean active = true;
}