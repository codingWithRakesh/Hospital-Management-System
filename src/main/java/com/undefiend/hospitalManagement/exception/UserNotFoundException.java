package com.undefiend.hospitalManagement.exception;

public class UserNotFoundException extends Exception{
    private String message;
    public UserNotFoundException(String message){
        super(message);
    }
}
