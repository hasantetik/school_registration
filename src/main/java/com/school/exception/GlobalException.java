package com.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleEntityNotFoundException(EntityNotFoundException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatus(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatus(HttpStatus.ALREADY_REPORTED.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorObject, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleConstraintViolationException(ConstraintViolationException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatus(HttpStatus.ALREADY_REPORTED.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorObject, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleCourseRegistrationException(CourseRegistrationException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatus(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(exception.getMessage());
        errorObject.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
}
