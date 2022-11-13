package com.stepstone.quiz.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "QUESTION")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description",unique = true)
    private String description;

    @OneToMany
    @JoinTable(
            name="QUESTION_ANSWER_MAPPING",
            joinColumns = @JoinColumn( name="question_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn( name="answer_id", referencedColumnName="id")
    )
    private List<Answer> answers;
}
