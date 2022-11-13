package com.stepstone.quiz.dto;

import lombok.Builder;
import lombok.Value;

/**
 * DTO Class to return Question Info(Id & description) to user.
 */
@Value
@Builder
public class QuestionDto {
    private final long id;
    private final String description;
}
