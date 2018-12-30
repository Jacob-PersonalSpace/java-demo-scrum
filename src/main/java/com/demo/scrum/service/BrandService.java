package com.demo.scrum.service;

import com.demo.scrum.domain.Brand;
import com.demo.scrum.domain.Customer;
import com.demo.scrum.repository.BrandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Brand save(String customerID, String name) {
        Customer customer = customerService.get(customerID);
        return brandRepository.save(new Brand(customer, name));
    }

    public Brand findByName(String name) {
        return brandRepository.findByName(name);
    }

}