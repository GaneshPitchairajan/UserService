package com.example.UserService.DTOs;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestdto {
    @NotBlank(message = "Email Should not be blank")
    @Email(message = "Provided Mail Id is not valid")
    String email;

    @NotBlank(message = "Password should not be blank")
    String password;
}
