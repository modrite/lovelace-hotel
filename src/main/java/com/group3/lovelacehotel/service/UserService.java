package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.User;
import com.group3.lovelacehotel.model.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);

    UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException;

    User findByEmail(String email);

    void updatePassword(String password, Long userId);

}
