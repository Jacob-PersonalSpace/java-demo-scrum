package com.demo.scrum.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "brands")
@Data
@NoArgsConstructor
public class Brand extends BaseDocument {

    public Brand(Customer customer, String name) {
        this.customer = customer;
        this.name = name;
    }

    @DBRef
    private Customer customer;
    private String name;

}
