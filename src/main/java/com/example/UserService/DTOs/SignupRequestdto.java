package com.example.UserService.DTOs;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestdto {
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
    private String password;

}
