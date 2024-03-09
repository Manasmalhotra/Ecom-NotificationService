package com.example.notificationservice.Exceptions;

public class VerificationFailedException extends RuntimeException{

    public VerificationFailedException(String message){
        super(message);
    }
}
