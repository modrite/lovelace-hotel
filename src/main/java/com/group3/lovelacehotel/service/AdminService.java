package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Admin;
import com.group3.lovelacehotel.model.Customer;
import com.group3.lovelacehotel.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void saveAdmin(Admin admin) {

        adminRepository.save(admin);
    }

    public boolean validate(Long id, String password) {
        Optional<Admin> admin = adminRepository.findById(id);
        if ((admin) != null) {
            if (admin.get().getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else
            return false;

    }
}
