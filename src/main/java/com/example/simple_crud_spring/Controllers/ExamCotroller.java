package com.example.simple_crud_spring.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.simple_crud_spring.Models.Exam;
import com.example.simple_crud_spring.Repository.ExapRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/exam")
public class ExamCotroller {

    @Autowired
    private ExapRepo exapRepo;

    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam) {
        try {
            Exam savedStudent = exapRepo.save(exam);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExam() {
        try {
            List<Exam> exams = exapRepo.findAll();
            if (exams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(exams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable String id) {
        Optional<Exam> examData = exapRepo.findById(id);
        if (examData.isPresent()) {
            return new ResponseEntity<>(examData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExamById(@PathVariable String id) {
        try {
            exapRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable String id, @RequestBody Exam updatedExam) {
        Optional<Exam> examData = exapRepo.findById(id);
        if (examData.isPresent()) {
            Exam exam = examData.get();
            exam.setExam_major(updatedExam.getExam_major());
            exam.setLeacture(updatedExam.getLeacture());
            exam.setRoom(updatedExam.getRoom());
            exam.setDate_examp(updatedExam.getDate_examp());
            exam.setGuidance_counselor(updatedExam.getGuidance_counselor());
            exam.setClass_controller(updatedExam.getClass_controller());
            return new ResponseEntity<>(exapRepo.save(exam), HttpStatus.OK);
        } else {
            updatedExam.setExam_id(id);
            return new ResponseEntity<>(exapRepo.save(updatedExam), HttpStatus.CREATED);
        }
    }

}
