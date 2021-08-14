package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.*;
import com.group3.lovelacehotel.model.dto.AdminRegistrationDto;
import com.group3.lovelacehotel.model.dto.UserRegistrationDto;
import com.group3.lovelacehotel.repository.UserRepository;
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
public class UserServiceImpl implements UserService,AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(
            registrationDto.getName(),
            registrationDto.getSurname(),
            registrationDto.getPhoneNumber(),
            registrationDto.getEmail(),
            passwordEncoder.encode(registrationDto.getPassword()),
            Arrays.asList(new Role("ROLE_CUSTOMER")));
        return userRepository.save(user);
    }

    @Override
    public User save(AdminRegistrationDto adminRegistrationDto) {
        User user = new User(
                adminRegistrationDto.getName(),
                adminRegistrationDto.getSurname(),
                adminRegistrationDto.getPhoneNumber(),
                adminRegistrationDto.getEmail(),
                passwordEncoder.encode(adminRegistrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_ADMIN")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }

}
