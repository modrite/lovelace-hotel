package com.group3.lovelacehotel.repository;

import com.group3.lovelacehotel.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(Long id);

    Admin findByEmail(String email);

}
