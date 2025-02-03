package com.example.simple_crud_spring.Models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Schedules {

    @Id
    private String schedules_id;

    private String exam_id;

    private String student_id;

    private LocalDate scheduled_at;

    private String status;
}
