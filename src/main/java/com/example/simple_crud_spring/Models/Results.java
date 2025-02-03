package com.example.simple_crud_spring.Models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Results")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Results {

    @Id
    private String result_id;

    private String exam_id;

    private String student_id;

    private Number score;

    private String total_marks;

    private String status;

    private LocalDate created_at;
}
