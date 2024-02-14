package com.example.hexagonalarchitecture.infraestructure.rest.advice;

import com.example.hexagonalarchitecture.infraestructure.adapter.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleEmptyInput(UserException emptyInputException){
        return new ResponseEntity<String>(emptyInputException.getErrorMessage(), emptyInputException.getErrorCode());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        String errorMessage = "Duplicate entry: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
