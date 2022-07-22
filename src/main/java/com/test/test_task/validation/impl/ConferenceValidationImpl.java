package com.test.test_task.validation.impl;

import com.test.test_task.entity.Conference;
import com.test.test_task.validation.EntityValidation;
import org.springframework.stereotype.Component;

@Component
public class ConferenceValidationImpl implements EntityValidation<Conference> {
    @Override
    public Boolean isValid(Conference conference) {
        return conference.getName() != null &&
                !conference.getName().isEmpty() &&
                conference.getName().length() != 0;
    }
}
