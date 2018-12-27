package com.demo.scrum.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {

    @NotNull(message = "Input name can not be null.")
    private String name;
    @NotNull(message = "Input password can not be null.")
    private String password;

}