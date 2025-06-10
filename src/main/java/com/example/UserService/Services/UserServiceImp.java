package com.example.UserService.Services;

import com.example.UserService.Models.Users;
import com.example.UserService.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImp implements UserServices{

    UserRepo userRepo;
    @Autowired
    public UserServiceImp(UserRepo repo){
        this.userRepo=repo;
    }
    @Override
    public Users Signup(String name, String email, String password) {
        Optional<Users> user= userRepo.findByEmail(email);
        if (user.isEmpty()){
            System.out.println("User can be created!");
            Users users =new Users();
            users.setEmail(email);
            users.setPassword(password);
            users.setName(name);
            userRepo.save(users);
            return users;
        }
        else{
            System.out.println("User with the same mail id is already present !");
            return null;
        }

    }

    @Override
    public Users LogIn(String email, String password) {
        Optional<Users> user=userRepo.findByEmail(email);
        if (user.isEmpty()){
            return null;
        }
        else{
            return user.get();
        }
    }
}
