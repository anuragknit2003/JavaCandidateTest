package com.stepstone.quiz.exception;

import com.stepstone.quiz.controller.QuestionController;
import com.stepstone.quiz.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ApiErrorTest {
    private MockMvc mockMvc;

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(questionController).setControllerAdvice(new ServicesExceptionHandler()).build();
    }

    @Test
    void shouldReturnClientErrorCodeWhenRequestDataIsNotValid() throws Exception {
        when(questionService.findQuestionById(anyLong())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/questions/1")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(NOT_FOUND.getReasonPhrase()))
                .andExpect(jsonPath("$.status").value(NOT_FOUND.name()));
    }

    @Test
    void shouldReturnServerErrorCodeWhenRequestDataIsNotValid() throws Exception {
        when(questionService.findQuestionById(anyLong())).thenThrow(new RuntimeException());
        this.mockMvc.perform(get("/questions/2")).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(INTERNAL_SERVER_ERROR.getReasonPhrase()))
                .andExpect(jsonPath("$.status").value(INTERNAL_SERVER_ERROR.name()));
    }
}
