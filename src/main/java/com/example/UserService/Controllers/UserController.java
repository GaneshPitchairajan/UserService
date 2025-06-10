package com.example.UserService.Controllers;

import com.example.UserService.DTOs.SignupRequestdto;
import com.example.UserService.Models.Users;
import com.example.UserService.Services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserServiceImp userServiceImp;
    @Autowired
    public UserController(UserServiceImp userServiceImp){
        this.userServiceImp=userServiceImp;
    }
    @PostMapping("/signup")
        public void login(@RequestBody SignupRequestdto request){
        System.out.println("login done");
        Users createduser= userServiceImp.Signup(request.getName(), request.getEmail(),request.getPassword());
        if (createduser==null){
            System.out.println("From Controller :THE USER "+request.getEmail()+" IS ALREADY PRESENT!");
        }
        else{
            System.out.println("From Controller :THE USER "+request.getEmail()+" IS CREATED !");
        }


    }
}
