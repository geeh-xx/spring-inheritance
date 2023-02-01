package com.interview.spring.codelitt.infrastructure.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class MemberValidationException extends RuntimeException{

    public MemberValidationException(String message) {
        super(message);
    }
}
