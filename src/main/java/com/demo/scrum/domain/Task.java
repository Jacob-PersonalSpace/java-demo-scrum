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

    @ManyToOne
    @JoinColumn
    private Project project;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
