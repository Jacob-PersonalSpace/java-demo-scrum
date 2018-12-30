package com.demo.scrum.service;

import com.demo.scrum.domain.Customer;
import com.demo.scrum.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Customer save(String firstName, String lastName) {
        return customerRepository.save(new Customer(firstName, lastName));
    }

    public Customer get(String customerID) {
        return customerRepository.findById(customerID).get();
    }

    public Customer findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public Customer update(String firstName, String lastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        Update update = new Update();
        update.set("lastName", lastName);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        return mongoTemplate.findAndModify(query, update, options, Customer.class);
    }

}