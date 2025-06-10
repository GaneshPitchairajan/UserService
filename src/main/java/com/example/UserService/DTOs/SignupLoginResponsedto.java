package com.example.UserService.DTOs;

import com.example.UserService.Models.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupLoginResponsedto {
    Long id;
    String name;
    String email;

    public SignupLoginResponsedto(Long id, String name, String email) {
        this.id=id;
        this.name=name;
        this.email=email;
    }

    public static SignupLoginResponsedto fromEntity(Users user) {
        return new SignupLoginResponsedto(user.getId(), user.getName(), user.getEmail());
    }

}
