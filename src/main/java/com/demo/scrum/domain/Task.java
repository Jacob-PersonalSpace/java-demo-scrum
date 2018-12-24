package com.demo.scrum.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull
    @JoinColumn
    private Project project;

    @Column(length = 50)
    @NotNull
    private String name;

    @Column(length = 500)
    private String description;

    @ManyToOne
    @NotNull
    @JoinColumn
    private TaskStatus status;

    @ManyToOne
    @NotNull
    @JoinColumn
    @CreatedBy
    private User creator;

    @ManyToOne
    @JoinColumn
    private User assigner;

    @ManyToOne
    @JoinColumn
    private User reporter;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Task() {
    }

    public Task(Project project, String name, String description, TaskStatus status, User assigner, User reporter) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.status = status;
        this.assigner = assigner;
        this.reporter = reporter;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
