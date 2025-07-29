package com.example.UserService.Repositories;

import com.example.UserService.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> deleteByEmail(String email);
}
