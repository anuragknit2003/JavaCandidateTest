package com.stepstone.quiz.service;

import com.stepstone.quiz.dto.QuestionDto;
import com.stepstone.quiz.dto.ValidationResultDto;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    /**
     * Returns all questions stored in question repository
     * @return List of {@link QuestionDto}
     */
    List<QuestionDto> findAll();

    /**
     * Finds and returns a Question by Id in repository
     * @param questionId Question Id
     * @return {@link QuestionDto}
     */
    Optional<QuestionDto> findQuestionById(long questionId);

    /**
     * Validates if passed answer id is correct for corresponding question id
     * @param questionId Question Id
     * @param answer Answer
     * @return Boolean value indicating the outcome
     */
    ValidationResultDto validateAnswer(long questionId, final String answer);

    /**
     * Returns all questions mapped to a particular quiz type
     * @param quizType Quiz Type
     * @return List of {@link QuestionDto}
     */
    List<QuestionDto> findAllQuestionsForQuizType(String quizType);
}
