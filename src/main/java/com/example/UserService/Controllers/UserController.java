package com.example.UserService.Controllers;

import com.example.UserService.DTOs.LoginRequestdto;
import com.example.UserService.DTOs.SignupRequestdto;
import com.example.UserService.DTOs.SignupLoginResponsedto;
import com.example.UserService.Models.Users;
import com.example.UserService.Services.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/signup")
    public SignupLoginResponsedto signup(@Valid @RequestBody SignupRequestdto request) {
        System.out.println("signup done");
        Users createduser = userServiceImp.Signup(request.getName(), request.getEmail(), request.getPassword());
        System.out.println("From Controller :THE USER " + request.getEmail() + " IS CREATED !");
        return SignupLoginResponsedto.fromEntity(createduser);

    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestdto loginRequestdto) {
        String token = userServiceImp.LogIn(loginRequestdto.getEmail(), loginRequestdto.getPassword());
        return token;
        //return SignupLoginResponsedto.fromEntity(users);
    }

    @DeleteMapping("/")
    public SignupLoginResponsedto delete(@Valid @RequestBody LoginRequestdto deleteUserdto){
        Users deleted=userServiceImp.Delete(deleteUserdto.getEmail(), deleteUserdto.getPassword());
        return SignupLoginResponsedto.fromEntity(deleted);
    }
}
