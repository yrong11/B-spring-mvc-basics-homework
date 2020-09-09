package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity handleUserException(MethodArgumentNotValidException exception){
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(exception.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.badRequest().body(errorResult);

    }

    @ExceptionHandler(value = {UserExistException.class})
    public ResponseEntity handleUserExistException(UserExistException exception){
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(errorResult);
    }
}
