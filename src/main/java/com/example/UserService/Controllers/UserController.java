package com.example.UserService.Controllers;

import com.example.UserService.DTOs.LoginRequestdto;
import com.example.UserService.DTOs.SignupRequestdto;
import com.example.UserService.DTOs.SignupLoginResponsedto;
import com.example.UserService.Exception.UserExistException;
import com.example.UserService.Models.Users;
import com.example.UserService.Services.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    UserServiceImp userServiceImp;
    @Autowired
    public UserController(UserServiceImp userServiceImp){
        this.userServiceImp=userServiceImp;
    }

    @PostMapping("/signup")
    private SignupLoginResponsedto signup(@Valid @RequestBody SignupRequestdto request){
        System.out.println("signup done");
        Users createduser= userServiceImp.Signup(request.getName(), request.getEmail(),request.getPassword());
        if (createduser==null){
            throw new UserExistException("From Controller :THE USER "+request.getEmail()+" IS ALREADY PRESENT!");

        }
        else{
            System.out.println("From Controller :THE USER "+request.getEmail()+" IS CREATED !");
            return SignupLoginResponsedto.fromEntity(createduser);
        }
    }

    @PostMapping("/login")
    private SignupLoginResponsedto login(@Valid @RequestBody LoginRequestdto loginRequestdto){
        Users users=userServiceImp.LogIn(loginRequestdto.getEmail(), loginRequestdto.getPassword());
        if(users == null){
            throw new RuntimeException("User not Exist , kindly SignUp before LogIn");
        }
        return SignupLoginResponsedto.fromEntity(users);
    }
}
