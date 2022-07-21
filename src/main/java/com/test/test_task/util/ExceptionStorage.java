package com.test.test_task.util;

public class ExceptionStorage {

    private ExceptionStorage(){}

    public static String conferenceNotExists(Long conferenceId){
        return String.format("Conference with id: %d not exists!", conferenceId);
    }

    public static String conferenceUnavailable(Long conferenceId){
        return String.format("Conference with id: %d is unavailable!", conferenceId);
    }

    public static String participantNotIncluded(Long participantId){
        return String.format("Participant is not included in conference with id: %d", participantId);
    }

    public static String participantNotExists(Long participantId){
        return String.format("Participant with id: %d not exists!", participantId);
    }



}
