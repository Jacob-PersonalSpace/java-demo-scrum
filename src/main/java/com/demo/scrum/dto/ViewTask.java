package com.demo.scrum.dto;

import java.util.Date;

import com.demo.scrum.domain.Task;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewTask {

    public ViewTask(Integer projectID, String projectName, String name, String description, Integer statusID,
            String statusName, Integer assignerID, String assignerName, Integer reporterID, String reporterName) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.name = name;
        this.description = description;
        this.statusID = statusID;
        this.statusName = statusName;
        this.assignerID = assignerID;
        this.assignerName = assignerName;
        this.reporterID = reporterID;
        this.reporterName = reporterName;
    }

    public ViewTask(Task task) {
        id = task.getId();
        projectID = task.getProject().getId();
        name = task.getName();
        description = task.getDescription();
        statusID = task.getStatus().getId();
        creatorID = task.getCreator() != null ? task.getCreator().getId() : null;
        assignerID = task.getAssigner() != null ? task.getAssigner().getId() : null;
        reporterID = task.getReporter() != null ? task.getReporter().getId() : null;
        createdAt = task.getCreatedAt();
        updatedAt = task.getUpdatedAt();
    }

    private Integer id;
    private Integer projectID;
    private String projectName;
    private String name;
    private String description;
    private Integer statusID;
    private String statusName;
    private Integer creatorID;
    private Integer assignerID;
    private String assignerName;
    private Integer reporterID;
    private String reporterName;
    private Date createdAt;
    private Date updatedAt;

}
