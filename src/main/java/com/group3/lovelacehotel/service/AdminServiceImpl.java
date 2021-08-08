package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Admin;
import com.group3.lovelacehotel.model.AdminRegistrationDto;
import com.group3.lovelacehotel.model.Role;
import com.group3.lovelacehotel.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin save(AdminRegistrationDto adminRegistrationDto) {Admin admin = new Admin(
            adminRegistrationDto.getName(),
            adminRegistrationDto.getSurname(),
            adminRegistrationDto.getPhoneNumber(),
            adminRegistrationDto.getEmail(),
            passwordEncoder.encode(adminRegistrationDto.getPassword()),
            Arrays.asList(new Role("ROLE_ADMIN")));
        return adminRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByEmail(username);
        if(admin == null) {
            throw new UsernameNotFoundException("Invalid email address or password.");
        }
        return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(),
                mapRolesToAuthorities(admin.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
