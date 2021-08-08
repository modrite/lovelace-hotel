package com.group3.lovelacehotel.controller;

import com.group3.lovelacehotel.model.AdminRegistrationDto;
import com.group3.lovelacehotel.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-registration")
public class AdminRegistrationController {

    private final AdminService adminService;

    public AdminRegistrationController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ModelAttribute("admin")
    public AdminRegistrationDto adminRegistrationDto() {
        return new AdminRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "admin-registration";
    }

    @PostMapping
    public String registerAdminAccount(@ModelAttribute("admin") AdminRegistrationDto adminRegistrationDto) {
        adminService.save(adminRegistrationDto);
        return "redirect:/admin-registration?success";
    }
}
