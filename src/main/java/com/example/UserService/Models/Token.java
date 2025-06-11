package com.example.UserService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "Tokens")
public class Token extends BaseModel {
    private  String token;
    private Date expiry_date;

    @ManyToOne
    private Users user;
}
