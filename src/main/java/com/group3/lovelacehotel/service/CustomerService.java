package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Customer;
import com.group3.lovelacehotel.model.CustomerRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerService extends UserDetailsService {

    Customer save(CustomerRegistrationDto registrationDto);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
