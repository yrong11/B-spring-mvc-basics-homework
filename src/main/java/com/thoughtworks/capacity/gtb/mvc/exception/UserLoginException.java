package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserLoginException extends Exception{
    String message;

    public UserLoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
