package com.demo.scrum.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WsRequest implements Serializable {

    private static final long serialVersionUID = -2985691602251688388L;
    @NotNull
    private String action;
    @NotNull
    private String payload;

}
