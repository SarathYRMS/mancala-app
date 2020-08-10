package com.sara.assessment.mancala.exception;

public class MancalaNotFoundException extends RuntimeException{
    public MancalaNotFoundException(String message) {
        super(message);
    }
}