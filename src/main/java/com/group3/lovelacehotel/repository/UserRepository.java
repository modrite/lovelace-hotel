package com.group3.lovelacehotel.repository;

import com.group3.lovelacehotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
}
