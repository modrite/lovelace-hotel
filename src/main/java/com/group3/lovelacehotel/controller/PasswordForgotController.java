package com.group3.lovelacehotel.controller;

import com.group3.lovelacehotel.model.Mail;
import com.group3.lovelacehotel.model.dto.PasswordForgotDto;
import com.group3.lovelacehotel.model.PasswordResetToken;
import com.group3.lovelacehotel.model.User;
import com.group3.lovelacehotel.repository.PasswordResetTokenRepository;
import com.group3.lovelacehotel.service.EmailService;
import com.group3.lovelacehotel.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired private PasswordResetTokenRepository tokenRepository;
    @Autowired private EmailService emailService;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return "forgot-password";
        }

        User user = userService.findByEmail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "We could not find an account with this e-mail address. " +
                    "Please enter your registered email address or create a new account");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@lovelace.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "http://localhost:8080/");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }
}
