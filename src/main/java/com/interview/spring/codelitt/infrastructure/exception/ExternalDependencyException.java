package com.interview.spring.codelitt.infrastructure.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FAILED_DEPENDENCY;

@ResponseStatus(FAILED_DEPENDENCY)
public class ExternalDependencyException extends RuntimeException{

    public ExternalDependencyException(String message) {
        super(message);
    }

}
