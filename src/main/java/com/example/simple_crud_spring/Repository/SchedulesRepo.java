package com.example.simple_crud_spring.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.simple_crud_spring.Models.Schedules;

public interface SchedulesRepo extends MongoRepository<Schedules, String> {

}
