package com.group3.lovelacehotel.repository;

import com.group3.lovelacehotel.model.Admin;
import com.group3.lovelacehotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(Long id);
}
