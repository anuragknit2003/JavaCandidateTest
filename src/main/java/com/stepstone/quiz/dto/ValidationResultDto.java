package com.stepstone.quiz.dto;

import lombok.Builder;
import lombok.Value;

/**
 * DTO Class to return Question Validation Result to user.
 */
@Value
@Builder
public class ValidationResultDto {
    private final boolean isAnswerValid;
}
