package com.group3.lovelacehotel.controller;

import com.group3.lovelacehotel.model.CustomerRegistrationDto;
import com.group3.lovelacehotel.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class CustomerRegistrationController {
    private CustomerService customerService;

    public CustomerRegistrationController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @ModelAttribute("customer")
    public CustomerRegistrationDto customerRegistrationDto() {
        return new CustomerRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerCustomerAccount(@ModelAttribute("customer") CustomerRegistrationDto registrationDto) {
        customerService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
