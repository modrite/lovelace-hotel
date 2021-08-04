package com.group3.lovelacehotel.service;

import com.group3.lovelacehotel.model.Customer;
import com.group3.lovelacehotel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public boolean validate(Long id, String password) {
        Optional<Customer> customer = customerRepository.findById(id);
        if ((customer) != null) {
            if (customer.get().getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else
            return false;

    }

}
