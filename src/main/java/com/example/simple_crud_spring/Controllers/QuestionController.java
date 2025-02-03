package com.example.simple_crud_spring.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple_crud_spring.Models.Exam;
import com.example.simple_crud_spring.Models.Questions;
import com.example.simple_crud_spring.Repository.ExapRepo;
import com.example.simple_crud_spring.Repository.QuestionRepo;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private ExapRepo exapRepo;

    @GetMapping
    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            List<Questions> questions = questionRepo.findAll();
            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Questions> addResult(@RequestBody Questions questions) {
        try {

            // Check if exam_id exists
            Optional<Exam> exam = exapRepo.findById(questions.getExam_id());
            if (exam.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Exam not found
            }

            // Save the result if both IDs are valid
            Questions saveQuestions = questionRepo.save(questions);
            return new ResponseEntity<>(saveQuestions, HttpStatus.CREATED);

        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle any errors
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Questions> updateQuestions(@PathVariable String id, @RequestBody Questions updateQuestions) {
        Optional<Questions> questionsData = questionRepo.findById(id);
        if (questionsData.isPresent()) {
            Questions questions = questionsData.get();
            questions.setExam_id(updateQuestions.getExam_id());
            questions.setQuestion(updateQuestions.getQuestion());
            questions.setQuestion_type(updateQuestions.getQuestion_type());
            return new ResponseEntity<>(questionRepo.save(questions), HttpStatus.OK);
        } else {
            updateQuestions.setCard_id(id);
            return new ResponseEntity<>(questionRepo.save(updateQuestions), HttpStatus.CREATED);
        }
    }
}
