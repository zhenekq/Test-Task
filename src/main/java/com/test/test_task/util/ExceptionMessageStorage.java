package com.test.test_task.util;

public class ExceptionMessageStorage {

    private ExceptionMessageStorage(){}

    public static String conferenceNotValid(){
        return "Conference data is not valid!";
    }

    public static String conferenceNotExists(Long conferenceId){
        return String.format("Conference with id: %d not exists!", conferenceId);
    }

    public static String conferenceUnavailable(Long conferenceId){
        return String.format("Conference with id: %d is unavailable!", conferenceId);
    }

    public static String conferenceExists(String name){
        return String.format("Conference with name: %s is exists!", name);
    }

    public static String participantNotIncluded(Long participantId){
        return String.format("Participant is not included in conference with id: %d", participantId);
    }

    public static String participantIsNotValid(){
        return "Participant data is not valid";
    }

    public static String participantNotExists(Long participantId){
        return String.format("Participant with id: %d not exists!", participantId);
    }

    public static String participantExists(String username){
        return String.format("Participant with name: %s exists!", username);
    }

    public static String roomNotExists(Long roomId){
        return String.format("Room with id: %d not exists!", roomId);
    }

    public static String roomNotAttached(Long conferenceId){
        return String.format("Room is not attached to conference with id: %d not exists!", conferenceId);
    }

    public static String roomIsFull(Long roomId){
        return String.format("Room with id: %d is full!", roomId);
    }

    public static String roomIsNotValid(){
        return "Room data is not valid!";
    }

    public static String roomIsNotRelated(Long roomId){
        return String.format("Room with id: %d is not related to conference", roomId);
    }
    public static String roomIsOccupied(Long roomId){
        return String.format("Room with id: %d is occupied by another conference", roomId);
    }


}
