package com.lits.SpringHomework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalCourseSearchException extends RuntimeException {

    public IllegalCourseSearchException(String message) {
        super(message);
    }
}
