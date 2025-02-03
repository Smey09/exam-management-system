package com.example.simple_crud_spring.Models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Exam")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Exam {

    @Id
    private String exam_id;

    private String exam_major;

    private String leacture;

    private String room;

    private LocalDate date_examp;

    private String guidance_counselor;

    private String class_controller;
}
