package com.example.UserService.Services;

import com.example.UserService.Exception.PasswordWrongException;
import com.example.UserService.Exception.UserExistException;
import com.example.UserService.Models.Users;
import com.example.UserService.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
@Service
public class UserServiceImp implements UserServices{

    UserRepo userRepo;
    BCryptPasswordEncoder encoder;
    @Autowired
    public UserServiceImp(UserRepo repo,BCryptPasswordEncoder encoder){
        this.userRepo=repo;
        this.encoder=encoder;
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
    public Users LogIn(String email, String password) {
        Optional<Users> user=userRepo.findByEmail(email);
        if (user.isEmpty()){
            return null;
        }
        else{
            if (encoder.matches( password,user.get().getPassword())){
                return user.get();
            }
            throw new PasswordWrongException("The Given Password is wrong");

        }
    }
}
