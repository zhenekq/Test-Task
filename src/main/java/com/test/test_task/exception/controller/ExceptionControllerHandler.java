package com.test.test_task.exception.controller;

import com.test.test_task.exception.data.ErrorBody;
import com.test.test_task.exception.variants.BusinessLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorBody> handleException(BusinessLogicException exception){
        return new ResponseEntity<>(new ErrorBody(exception.getStatus().name(), exception.getStatus().value(), exception.getMessage()), exception.getStatus());
    }
}
