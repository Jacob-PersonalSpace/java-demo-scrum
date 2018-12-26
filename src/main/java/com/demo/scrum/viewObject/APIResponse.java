package com.demo.scrum.viewObject;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class APIResponse<T> {
    private Integer code;
    private Boolean status = false;
    private T data;

    public APIResponse(int code, Boolean status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }
}
