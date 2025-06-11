package com.example.UserService.Exception;

public class PasswordWrongException extends  RuntimeException{
    public PasswordWrongException(String message){
        super(message);
    }
}
