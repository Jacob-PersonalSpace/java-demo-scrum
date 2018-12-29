package com.demo.scrum.controller;

import com.demo.scrum.domain.Customer;
import com.demo.scrum.dto.response.APIResponse;
import com.demo.scrum.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        Customer newCustomer = customerService.save(customer.getFirstName(), customer.getLastName());

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), true, newCustomer));
    }

    @GetMapping(value = "/{firstName}")
    public ResponseEntity<?> getByFirstName(@PathVariable String firstName) {
        Customer targetCustomer = customerService.findByFirstName(firstName);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), true, targetCustomer));
    }

}