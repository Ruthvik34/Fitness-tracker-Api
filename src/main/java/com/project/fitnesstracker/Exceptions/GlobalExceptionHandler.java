package com.project.fitnesstracker.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.fitnesstracker.Exceptions.CustomExceptions.CustomException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        
        Map<String, String> errorMessage = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
          ((org.springframework.validation.FieldError) error).getField();
           error.getDefaultMessage();

           errorMessage.put(((org.springframework.validation.FieldError) error).getField(), error.getDefaultMessage());
        
        });
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

        @ExceptionHandler(CustomException.UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(CustomException.UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

        
        @ExceptionHandler(CustomException.ActivityNotFoundException.class)
        public ResponseEntity<String> handleActivityNotFoundException(CustomException.ActivityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
}
