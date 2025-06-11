package com.example.UserService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public BCryptPasswordEncoder getbcryptpasswordencoder(){
        return new BCryptPasswordEncoder();
    }
}
