package com.stepstone.quiz.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnValidResponseIfRequestedQuestionIdExists() throws Exception {
        this.mockMvc.perform(get("/questions/10000")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':10000,'description':'When the National Forest Policy was formed in India'}"));
    }

    @Test
    void shouldReturnValidResponseWithAllQuestionsInRepository() throws Exception {
        this.mockMvc.perform(get("/questions/")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':10000,'description':'When the National Forest Policy was formed in India'},{'id':10001,'description':'Oxidation is a process which involves'}]"));
    }

    @Test
    void shouldReturnErrorResponseIfRequestedQuestionIdNotExists() throws Exception {
        this.mockMvc.perform(get("/questions/1")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(NOT_FOUND.getReasonPhrase()))
                .andExpect(jsonPath("$.status").value(NOT_FOUND.name()));
    }

    @Test
    void shouldReturnTrueIfPassesAnswerIsCorrect() throws Exception {
        this.mockMvc.perform(post("/questions/10000/validate-answer").param("answer","1952")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'answerValid':true}"));
    }
    @Test
    void shouldReturnFalseIfPassesAnswerIsIncorrect() throws Exception {
        this.mockMvc.perform(post("/questions/10000/validate-answer").param("answer","XXX")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'answerValid':false}"));
    }

    @Test
    void shouldReturnAllQuestionsForQuizType() throws Exception {
        this.mockMvc.perform(post("/questions/find-questions-for-quiz-type").param("quizType","Science Quiz")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':10001,'description':'Oxidation is a process which involves'}]"));
    }
}
