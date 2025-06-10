package com.example.UserService.Exception;

public class UserExistException extends RuntimeException {
    public UserExistException(String message){
        super(message);
    }
}
