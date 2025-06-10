package com.example.UserService.Services;

import com.example.UserService.Models.Users;

import java.util.Optional;

public interface UserServices {
    public Users Signup(String name, String email, String password);

    public Optional<Users> LogIn(String email,String password);


}
