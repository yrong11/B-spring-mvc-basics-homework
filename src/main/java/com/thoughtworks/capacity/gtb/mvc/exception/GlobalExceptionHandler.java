package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity handleUserException(MethodArgumentNotValidException exception){
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(exception.getBindingResult().getFieldError().getDefaultMessage());
        errorResult.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(errorResult);

    }

    @ExceptionHandler(value = {UserExistException.class})
    public ResponseEntity handleUserExistException(UserExistException exception){
        ErrorResult errorResult = ErrorResult.builder().message(exception.getMessage()).code(HttpStatus.BAD_REQUEST.value()).build();
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(value = {UserLoginException.class})
    public ResponseEntity handleUserLoginException(UserLoginException exception){
        ErrorResult errorResult = ErrorResult.builder().message(exception.getMessage()).code(HttpStatus.BAD_REQUEST.value()).build();
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstrainException(ConstraintViolationException exception){
        ErrorResult errorResult = ErrorResult.builder().message(exception.getMessage())
                .code(HttpStatus.BAD_REQUEST.value()).build();
        return ResponseEntity.badRequest().body(errorResult);
    }
}
