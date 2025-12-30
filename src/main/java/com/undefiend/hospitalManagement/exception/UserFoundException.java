package com.undefiend.hospitalManagement.exception;

public class UserFoundException extends Exception{
    private String message;
    public UserFoundException(String message){
        super(message);
    }
}
