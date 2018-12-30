package com.demo.scrum.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class BaseDocument {

    @Id
    private String id;

}