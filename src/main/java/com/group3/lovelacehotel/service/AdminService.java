package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Admin;
import com.group3.lovelacehotel.model.AdminRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AdminService extends UserDetailsService {

    Admin save(AdminRegistrationDto adminRegistrationDto);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
