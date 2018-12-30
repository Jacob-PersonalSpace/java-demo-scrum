package com.demo.scrum.dto.response;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WsResponse {

    @NotNull
    private String action;
    @NotNull
    private String payload;

}
