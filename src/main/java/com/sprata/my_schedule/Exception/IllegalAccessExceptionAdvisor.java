package com.sprata.my_schedule.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class IllegalAccessExceptionAdvisor {
    @ExceptionHandler(java.lang.IllegalAccessException.class)
    public String processValidationError(java.lang.IllegalAccessException exception) {

        return exception.getMessage();
    }
}
