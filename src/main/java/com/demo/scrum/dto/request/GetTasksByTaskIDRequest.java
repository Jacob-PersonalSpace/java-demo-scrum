package com.demo.scrum.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTasksByTaskIDRequest {

    @Min(1)
    @NotNull
    private Integer taskID;
    @NotNull
    @NotEmpty
    private String name;

}