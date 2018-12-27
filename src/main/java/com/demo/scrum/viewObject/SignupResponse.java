package com.demo.scrum.viewObject;

import com.demo.scrum.domain.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupResponse {

    public SignupResponse(User user) {
        id = user.getId();
        name = user.getName();
        active = user.getActive();
    }

    private Integer id;
    private String name;
    private Boolean active;

}