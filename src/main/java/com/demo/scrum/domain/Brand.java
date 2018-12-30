package com.demo.scrum.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "brands")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Brand extends BaseDocument {

    public Brand(Customer customer, String name) {
        this.customer = customer;
        this.name = name;
    }

    @DBRef(lazy = true)
    private Customer customer;
    private String name;

}
