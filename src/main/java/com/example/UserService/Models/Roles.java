package com.example.UserService.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity (name="roles")
@Setter
@Getter
public class Roles extends BaseModel {
    private String role_name;
}
