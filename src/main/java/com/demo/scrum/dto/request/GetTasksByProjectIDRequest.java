package com.demo.scrum.dto.request;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTasksByProjectIDRequest {

    @Min(1)
    private Integer productID;

}