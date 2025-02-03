package com.example.simple_crud_spring.Models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Questions")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Questions {
    @Id
    private String question_id;

    private String exam_id;

    private String question;

    private String question_type;

    private LocalDate created_at;

    private LocalDate updated_at;

    public void setCard_id(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCard_id'");
    }

}
