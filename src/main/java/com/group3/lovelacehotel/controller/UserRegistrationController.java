package com.group3.lovelacehotel.controller;

import com.group3.lovelacehotel.model.dto.UserRegistrationDto;
import com.group3.lovelacehotel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }

//    @PostMapping
//    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto registrationDto,
//                                      BindingResult result){
//
//        User existing = userService.findByEmail(registrationDto.getEmail());
//        if (existing != null){
//            result.rejectValue("email", null, "There is already an account registered with that email");
//        }
//
//        if (result.hasErrors()){
//            return "registration";
//        }
//
//        userService.save(registrationDto);
//        return "redirect:/registration?success";
//    }

}
