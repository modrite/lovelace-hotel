package com.group3.lovelacehotel.repository;

import com.group3.lovelacehotel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findFirstByNameIgnoreCase(String roleName);
}
