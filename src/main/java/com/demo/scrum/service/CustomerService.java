package com.demo.scrum.service;

import com.demo.scrum.domain.Customer;
import com.demo.scrum.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(String firstName, String lastName) {
        return customerRepository.save(new Customer(firstName, lastName));
    }

    public Customer findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

}