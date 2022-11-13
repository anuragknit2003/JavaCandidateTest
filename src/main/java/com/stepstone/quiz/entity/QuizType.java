package com.stepstone.quiz.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "QUIZ_TYPE")
@Data
public class QuizType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "description",unique = true)
    private String description;

    @OneToMany(mappedBy = "quizType")
    private List<Quiz> quiz;
}
