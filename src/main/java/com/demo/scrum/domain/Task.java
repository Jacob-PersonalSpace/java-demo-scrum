package com.demo.scrum.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
public class Task {

    public Task(Project project, String name, String description, TaskStatus status, User assigner, User reporter) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.status = status;
        this.assigner = assigner;
        this.reporter = reporter;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Project project;

    @Column(length = 50)
    @NotNull
    private String name;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User assigner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn
    @CreatedBy
    private User creator;

    @CreatedDate
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @LastModifiedBy
    private User updator;

    @LastModifiedDate
    private Date updatedAt;

}
