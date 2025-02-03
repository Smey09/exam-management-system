package com.example.simple_crud_spring.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.simple_crud_spring.Models.Results;

public interface ResultRepo extends MongoRepository<Results, String> {

}