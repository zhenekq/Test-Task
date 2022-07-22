package com.test.test_task.exception.controller;

import com.test.test_task.exception.data.ErrorBody;
import com.test.test_task.exception.variants.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorBody> handleException(BusinessLogicException exception) {
        return new ResponseEntity<>(new ErrorBody(exception.getStatus().name(), exception.getStatus().value(), exception.getMessage()), exception.getStatus());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<List<ErrorBody>> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(buildValidationErrors(exception.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<List<ErrorBody>> handleException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(buildMethodValidationErrors(exception.getAllErrors()), HttpStatus.BAD_REQUEST);
    }



    private List<ErrorBody> buildValidationErrors(
            Set<ConstraintViolation<?>> violations) {
        return violations.
                stream().
                map(violation ->
                        new ErrorBody(StreamSupport.stream(
                                        violation.getPropertyPath().spliterator(), false).
                                reduce((first, second) -> second).
                                orElse(null).
                                toString(),
                                HttpStatus.BAD_REQUEST.value(),
                                violation.getMessage()
                        )).collect(toList());
    }

    private List<ErrorBody> buildMethodValidationErrors(
            List<ObjectError> violations) {
        return violations.
                stream().
                map(violation ->
                        new ErrorBody(violation.getArguments()[0].toString(),
                                HttpStatus.BAD_REQUEST.value(),
                                violation.getDefaultMessage()
                        )).collect(toList());
    }
}
