package com.stepstone.quiz.service;

import com.stepstone.quiz.dto.QuestionDto;
import com.stepstone.quiz.dto.ValidationResultDto;
import com.stepstone.quiz.entity.Answer;
import com.stepstone.quiz.entity.Question;
import com.stepstone.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Implementation of
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<QuestionDto> findAll() {
        return StreamSupport.stream(questionRepository.findAll().spliterator(), false).
                map(entity -> mapEntityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionDto> findQuestionById(long questionId) {
        final Optional<Question> question = questionRepository.findById(questionId);
        return question.map(this::mapEntityToDto);
    }

    @Override
    public ValidationResultDto validateAnswer(long questionId, String answer) {
        final Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            final List<Answer> answers = question.get().getAnswers();
            return  ValidationResultDto.builder().
                    isAnswerValid(answers.stream().filter(ans -> ans.getDescription().equalsIgnoreCase(answer)).findAny().isPresent()).
                    build();
        }
        return ValidationResultDto.builder().isAnswerValid(false).build();
    }

    @Override
    public List<QuestionDto> findAllQuestionsForQuizType(String quizType) {
        return questionRepository.findAllQuestionsByQuizTypeNative(quizType).stream().
                map(entity -> mapEntityToDto(entity)).collect(Collectors.toList());
    }

    private QuestionDto mapEntityToDto(final Question entity) {
        return QuestionDto.builder().id(entity.getId()).description(entity.getDescription()).build();
    }
}
