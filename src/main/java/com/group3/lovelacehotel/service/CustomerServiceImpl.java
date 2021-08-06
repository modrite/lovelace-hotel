package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Customer;
import com.group3.lovelacehotel.model.CustomerRegistrationDto;
import com.group3.lovelacehotel.model.Role;
import com.group3.lovelacehotel.repository.CustomerRepository;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(CustomerRegistrationDto registrationDto) {Customer customer = new Customer(
            registrationDto.getName(),
            registrationDto.getSurname(),
            registrationDto.getPhoneNumber(),
            registrationDto.getEmail(),
            passwordEncoder.encode(registrationDto.getPassword()),
            Arrays.asList(new Role("ROLE_CUSTOMER")));
        return customerRepository.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(username);
        if(customer == null) {
            throw new UsernameNotFoundException("Invalid email address or password.");
        }
        return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
                mapRolesToAuthorities(customer.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
