package com.demo.scrum.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTasksByProjectIDRequest {

    @Min(1)
    @NotNull
    private Integer productID;

}