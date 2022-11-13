package com.stepstone.quiz.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 *  Exception Handler to catch exceptions thrown during processing of a request and
 *  build error response
 */
@ControllerAdvice
public class ServicesExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        if (ex instanceof ResourceNotFoundException) {
            final ApiError clientError = ApiError.builder().timestamp(LocalDateTime.now()).status(NOT_FOUND).errorMessage(NOT_FOUND.getReasonPhrase()).build();
            return new ResponseEntity(clientError,NOT_FOUND);
        } else {
            final ApiError serverError = ApiError.builder().timestamp(LocalDateTime.now()).status(INTERNAL_SERVER_ERROR).errorMessage(INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
            return new ResponseEntity(serverError,NOT_FOUND);
        }
    }
}
