package com.example.UserService.Services;

import com.example.UserService.Models.Users;

public interface UserServices {
    public Users Signup(String name, String email, String password);

    public String LogIn(String email, String password);


}
