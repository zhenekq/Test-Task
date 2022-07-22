package com.test.test_task.exception.variants;

import com.test.test_task.exception.BusinessAbstractException;
import org.springframework.http.HttpStatus;

public class BusinessLogicException extends BusinessAbstractException {

    public BusinessLogicException(String message, HttpStatus status) {
        super(message, status);
    }
}
