package com.example.simple_crud_spring.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple_crud_spring.Models.Exam;
import com.example.simple_crud_spring.Models.Results;
import com.example.simple_crud_spring.Models.Student;
import com.example.simple_crud_spring.Repository.ExapRepo;
import com.example.simple_crud_spring.Repository.ResultRepo;
import com.example.simple_crud_spring.Repository.StudentRepo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    private ResultRepo resultRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ExapRepo exapRepo;

    @PostMapping
    public ResponseEntity<Results> addResult(@RequestBody Results results) {
        try {

            // Check if exam_id exists
            Optional<Exam> exam = exapRepo.findById(results.getExam_id());
            if (exam.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Exam not found
            }

            // Check if student_id exists
            Optional<Student> student = studentRepo.findById(results.getStudent_id());
            if (student.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Student not found
            }

            // Save the result if both IDs are valid
            Results savedResult = resultRepo.save(results);
            return new ResponseEntity<>(savedResult, HttpStatus.CREATED);

        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle any errors
        }
    }

    @GetMapping
    public ResponseEntity<List<Results>> getAllResult() {
        try {
            List<Results> results = resultRepo.findAll();
            if (results.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Results> getStudentById(@PathVariable String id) {
        Optional<Results> resultData = resultRepo.findById(id);
        if (resultData.isPresent()) {
            return new ResponseEntity<>(resultData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // can't update

    @DeleteMapping("/{id}")
    public ResponseEntity<Results> deleteExamById(@PathVariable String id) {
        try {
            resultRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
