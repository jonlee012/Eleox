package com.example.eleox.repositories;

import com.example.eleox.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}
