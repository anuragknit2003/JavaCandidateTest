package com.stepstone.quiz.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "QUIZ")
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "description",unique = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "quizTypeId")
    private QuizType quizType;

    @OneToMany
    @JoinTable(
            name="QUIZ_QUESTION_MAPPING",
            joinColumns = @JoinColumn( name="quizId"),
            inverseJoinColumns = @JoinColumn( name="questionId")
    )
    private List<Question> questions;
}
