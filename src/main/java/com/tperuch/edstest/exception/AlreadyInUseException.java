package com.tperuch.edstest.exception;

public class AlreadyInUseException extends RuntimeException{
    public AlreadyInUseException(String message){
        super(message);
    }
}
