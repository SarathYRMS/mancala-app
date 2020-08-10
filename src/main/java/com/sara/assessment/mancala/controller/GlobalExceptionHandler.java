package com.sara.assessment.mancala.controller;

import com.sara.assessment.mancala.exception.MancalaException;
import com.sara.assessment.mancala.exception.MancalaInvalidRequestException;
import com.sara.assessment.mancala.exception.MancalaNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MancalaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(MancalaNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND_ERROR", exception.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MancalaException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(MancalaException exception) {
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", exception.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MancalaInvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(MancalaInvalidRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", exception.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

@Getter
@Setter
class ErrorResponse {
    private String type;
    private String message;
    private LocalDateTime timestamp;
    private int status;

    ErrorResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }
}

