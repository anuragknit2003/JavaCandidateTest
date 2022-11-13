package com.stepstone.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "ANSWER")
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description",unique = true)
    private String description;
}
