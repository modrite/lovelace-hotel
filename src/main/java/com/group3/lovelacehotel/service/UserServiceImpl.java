package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Role;
import com.group3.lovelacehotel.model.User;
import com.group3.lovelacehotel.model.dto.AdminRegistrationDto;
import com.group3.lovelacehotel.model.dto.UserRegistrationDto;
import com.group3.lovelacehotel.repository.RoleRepository;
import com.group3.lovelacehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, AdminService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User save(UserRegistrationDto registrationDto) {
        Role role = roleRepository.findFirstByNameIgnoreCase("ROLE_CUSTOMER")
                .orElseGet(() -> new Role("ROLE_CUSTOMER"));

        User user = new User(
                registrationDto.getName(),
                registrationDto.getSurname(),
                registrationDto.getPhoneNumber(),
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()),
                List.of(role));
        return userRepository.save(user);
    }

    @Override
    public User save(AdminRegistrationDto adminRegistrationDto) {
        Role role = roleRepository.findFirstByNameIgnoreCase("ROLE_ADMIN")
                .orElseGet(() -> new Role("ROLE_ADMIN"));

        User user = new User(
                adminRegistrationDto.getName(),
                adminRegistrationDto.getSurname(),
                adminRegistrationDto.getPhoneNumber(),
                adminRegistrationDto.getEmail(),
                passwordEncoder.encode(adminRegistrationDto.getPassword()),
                List.of(role));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }

}
