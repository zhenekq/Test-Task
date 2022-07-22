package com.test.test_task.validation.impl;

import com.test.test_task.entity.Participant;
import com.test.test_task.validation.EntityValidation;
import org.springframework.stereotype.Component;

@Component
public class ParticipantValidation implements EntityValidation<Participant> {
    @Override
    public Boolean isValid(Participant participant) {
        return participant.getUsername() != null &&
                !participant.getUsername().isEmpty() &&
                participant.getUsername().length() != 0;
    }
}
