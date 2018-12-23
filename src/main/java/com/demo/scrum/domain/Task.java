package com.demo.scrum.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(length = 500)
    private String description;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "task_status_id")
    private TaskStatus status;

    @Column(length = 100)
    private Date updatedAt;

    @ManyToOne
    @NotNull
    @JoinColumn
    private User assigner;

    @ManyToOne
    @NotNull
    @JoinColumn
    private User reporter;

    @Column(length = 100)
    private Date createdAt;

    @ManyToOne
    @NotNull
    @JoinColumn
    private User creator;
}