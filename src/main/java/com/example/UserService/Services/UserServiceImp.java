package com.example.UserService.Services;

import com.example.UserService.Exception.PasswordWrongException;
import com.example.UserService.Exception.UserExistException;
import com.example.UserService.Models.Token;
import com.example.UserService.Models.Users;
import com.example.UserService.Repositories.TokenRepo;
import com.example.UserService.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Optional;
@Service
public class UserServiceImp implements UserServices{

    UserRepo userRepo;
    BCryptPasswordEncoder encoder;
    JwtService jwtService;
    TokenRepo tokenRepo;
    @Autowired
    public UserServiceImp(UserRepo repo,BCryptPasswordEncoder encoder , JwtService jwtService,TokenRepo tokenRepo){
        this.userRepo=repo;
        this.encoder=encoder;
        this.jwtService=jwtService;
        this.tokenRepo=tokenRepo;
    }
    @Override
    public Users Signup(String name, String email, String password) {
        Optional<Users> user= userRepo.findByEmail(email);
        if (user.isEmpty()){
            System.out.println("User can be created!");
            Users users =new Users();
            users.setEmail(email);
            users.setPassword(encoder.encode(password));
            users.setName(name);
            userRepo.save(users);
            return users;
        }
        else{
            System.out.println("User with the same mail id is already present !");
            throw new UserExistException("From User Service :THE USER "+email+" IS ALREADY PRESENT!");

        }

    }

    @Override
    public String LogIn(String email, String password) {
        Optional<Users> user=userRepo.findByEmail(email);
        if (user.isEmpty()){
            throw new UserExistException("User not exists");
        }
        else{
            if (encoder.matches( password,user.get().getPassword())){
               String jwt = jwtService.generateToken(email);
               Token token= new Token();
               token.setUser(user.get());
               token.setExpiry_date(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 ));
               token.setToken(jwt);
               tokenRepo.save(token);
               return  jwt;
            }
            throw new PasswordWrongException("The Given Password is wrong");

        }
    }
}
