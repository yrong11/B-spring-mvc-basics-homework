package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserExistException extends Exception{
    String message = "用户已存在";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
