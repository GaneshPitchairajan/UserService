package com.example.UserService.Repositories;

import com.example.UserService.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token,Long> {

    Optional<Token> findFirstByUser_idOrderByCreatedateDesc(Long userId);
    void deleteByUser_id(Long userId);

}
