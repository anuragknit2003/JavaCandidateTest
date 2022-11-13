package com.stepstone.quiz.repository;

import com.stepstone.quiz.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Spring JPA Repository for Question Entity
 */
public interface QuestionRepository extends CrudRepository<Question, Long> {
    /**
     * Returns all questions mapped to a particular quiz type
     * @param quizType Quiz Type
     * @return Collection of {@link Question}
     */
    @Query(value = "WITH QUIZ_REP as (SELECT quiz.* FROM QUIZ quiz INNER JOIN QUIZ_TYPE quizType ON quiz.quizTypeId=quizType.id where quizType.description = ?1) " +
            "SELECT distinct question.* FROM QUESTION question JOIN QUIZ_QUESTION_MAPPING qqm ON question.id = qqm.question_id " +
            "JOIN QUIZ_REP qr ON qr.id = qqm.quiz_id", nativeQuery = true)
    Collection<Question> findAllQuestionsByQuizTypeNative(String quizType);
}
