package com.example.simple_crud_spring.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple_crud_spring.Models.Exam;
import com.example.simple_crud_spring.Models.Schedules;
import com.example.simple_crud_spring.Models.Student;
import com.example.simple_crud_spring.Repository.ExapRepo;
import com.example.simple_crud_spring.Repository.SchedulesRepo;
import com.example.simple_crud_spring.Repository.StudentRepo;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private SchedulesRepo schedulesRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ExapRepo exapRepo;

    @GetMapping
    public ResponseEntity<List<Schedules>> getAllSchedules() {
        try {
            List<Schedules> schedules = schedulesRepo.findAll();
            if (schedules.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Schedules> addResult(@RequestBody Schedules schedules) {
        try {

            // Check if exam_id exists
            Optional<Exam> exam = exapRepo.findById(schedules.getExam_id());
            if (exam.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Exam not found
            }

            // Check if student_id exists
            Optional<Student> student = studentRepo.findById(schedules.getStudent_id());
            if (student.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Student not found
            }

            // Save the result if both IDs are valid
            Schedules savedSchedules = schedulesRepo.save(schedules);
            return new ResponseEntity<>(savedSchedules, HttpStatus.CREATED);

        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle any errors
        }
    }

}
