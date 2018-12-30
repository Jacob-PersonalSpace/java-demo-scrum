package com.demo.scrum.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "customers")
@NoArgsConstructor
public class Customer extends BaseDocument {

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String firstName;
    private String lastName;

}