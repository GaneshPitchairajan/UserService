package com.example.UserService.Services;
import java.util.Date;
import com.example.UserService.Exception.PasswordWrongException;
import com.example.UserService.Exception.UserExistException;
import com.example.UserService.Models.Token;
import com.example.UserService.Models.Users;
import com.example.UserService.Repositories.TokenRepo;
import com.example.UserService.Repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.text.html.Option;
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
    @Transactional
    @Override
    public Users Delete(String email,String password){
        Optional<Users> deleteUser = userRepo.findByEmail(email);
        if(deleteUser.isPresent() && encoder.matches(password,deleteUser.get().getPassword())){
            tokenRepo.deleteByUser_id(deleteUser.get().getId());
            userRepo.deleteByEmail(deleteUser.get().getEmail());

        }
        else throw new PasswordWrongException("The Given Password is wrong or Given User is not present");

        return deleteUser.get();
    }
    @Override
    public String LogIn(String email, String password) {
        Optional<Users> user=userRepo.findByEmail(email);
        if (user.isEmpty()){
            throw new UserExistException("User not exists");
        }
        if (encoder.matches( password,user.get().getPassword())) {
            Optional<Token> token = tokenRepo.findFirstByUser_idOrderByCreatedateDesc(user.get().getId());
            if ((token.isPresent() && token.get().getExpiry_date().compareTo(new Date()) < 0) || token.isEmpty()) {
                String jwt = jwtService.generateToken(email);
                Token newToken = new Token();
                newToken.setUser(user.get());
                newToken.setExpiry_date(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
                newToken.setToken(jwt);
                tokenRepo.deleteByUser_id(user.get().getId());
                tokenRepo.save(newToken);
                return jwt;
            } else {

                return token.get().getToken();
            }
        }
        else throw new PasswordWrongException("The Given Password is wrong");

    }
}
