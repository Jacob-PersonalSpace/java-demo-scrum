package com.demo.scrum.dto.response;

import java.util.Date;

import com.demo.scrum.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTaskByTaskIDResponse {

    public GetTaskByTaskIDResponse(Task task) {
        id = task.getId();
        projectID = task.getProject().getId();
        name = task.getName();
        description = task.getDescription();
        statusID = task.getStatus().getId();
        assignerID = task.getAssigner() != null ? task.getAssigner().getId() : null;
        reporterID = task.getReporter() != null ? task.getReporter().getId() : null;
        creatorID = task.getCreator() != null ? task.getCreator().getId() : null;
        createdAt = task.getCreatedAt();
        updatorID = task.getUpdator() != null ? task.getUpdator().getId() : null;
        updatedAt = task.getUpdatedAt();
    }

    private Integer id;
    private Integer projectID;
    private String projectName;
    private String name;
    private String description;
    private Integer statusID;
    private String statusName;
    private Integer assignerID;
    private String assignerName;
    private Integer reporterID;
    private String reporterName;
    private Integer creatorID;
    private Date createdAt;
    private Integer updatorID;
    private Date updatedAt;

}
