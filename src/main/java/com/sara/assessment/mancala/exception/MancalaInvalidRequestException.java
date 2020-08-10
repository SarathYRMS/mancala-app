package com.sara.assessment.mancala.exception;

public class MancalaInvalidRequestException extends RuntimeException {
    public MancalaInvalidRequestException(String message) {
        super(message);
    }
}