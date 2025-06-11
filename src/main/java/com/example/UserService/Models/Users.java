package com.example.UserService.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="users")
public class Users extends BaseModel {
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany
    private List<Roles> roles;

}
