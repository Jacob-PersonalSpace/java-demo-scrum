package com.demo.scrum.repository;

import com.demo.scrum.domain.Brand;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepository extends MongoRepository<Brand, String> {
    Brand findByName(String name);
}
