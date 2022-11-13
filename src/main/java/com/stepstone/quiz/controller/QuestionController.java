package com.stepstone.quiz.controller;

import com.stepstone.quiz.dto.QuestionDto;
import com.stepstone.quiz.dto.ValidationResultDto;
import com.stepstone.quiz.exception.ResourceNotFoundException;
import com.stepstone.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Rest Endpoint to perform operations On Question.
 */
@RestController
@RequestMapping(QuestionController.ROOT_MAPPING)
public class QuestionController {
    public static final String ROOT_MAPPING = "/questions";

    @Autowired
    private QuestionService questionService;

    /**
     * Finds a Question by Id in repository and returns
     *
     * @param questionId Question Id
     * @return 200 With Question if Question Found, 404 if question doesn't exist
     */
    @RequestMapping(value = "/{questionId}", method = GET)
    @ResponseStatus(OK)
    public QuestionDto find(@PathVariable Long questionId) {
        return questionService.findQuestionById(questionId).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Validates if the answer provided is correct for a given question id
     *
     * @param questionId Question Id
     * @param answer     Answer
     * @return true if answer is correct , false otherwise
     */
    @RequestMapping(value = "/{questionId}/validate-answer", method = POST)
    @ResponseStatus(OK)
    public ValidationResultDto validateAnswer(@PathVariable Long questionId, @RequestParam(value = "answer") String answer) {
        return questionService.validateAnswer(questionId, answer);
    }

    /**
     * Returns All Questions in the repo
     *
     * @return All Questions in the repo
     */
    @RequestMapping(value = "/", method = GET)
    @ResponseStatus(OK)
    public List<QuestionDto> findAll() {
        return questionService.findAll();
    }

    /**
     * Returns All Questions in the repo depending on the type of quiz
     *
     * @return All Questions in the repo depending on the type of quiz
     */
    @RequestMapping(value = "/find-questions-for-quiz-type", method = POST)
    @ResponseStatus(OK)
    public List<QuestionDto> findAllQuestionsForAQuizTypeCategory(@RequestParam(value = "quizType") String quizType) {
        return questionService.findAllQuestionsForQuizType(quizType);
    }
}
