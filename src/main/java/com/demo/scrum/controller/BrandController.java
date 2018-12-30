package com.demo.scrum.controller;

import com.demo.scrum.domain.Brand;
import com.demo.scrum.dto.response.APIResponse;
import com.demo.scrum.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestParam String customerID, @RequestParam String name) {
        Brand newBrand = brandService.save(customerID, name);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), true, newBrand));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Brand brand = brandService.findByName(name);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), true, brand));
    }

}