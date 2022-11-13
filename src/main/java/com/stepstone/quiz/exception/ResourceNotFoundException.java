package com.stepstone.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Custom Exception to indicate resource doesn't exist
 */
public class ResourceNotFoundException extends ResponseStatusException {
    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
