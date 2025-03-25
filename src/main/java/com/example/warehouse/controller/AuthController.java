package com.example.warehouse.controller;

import com.example.warehouse.entity.User;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String getHomePage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult result, Model model) {
        if (!user.isPasswordsMatching()) {
            result.rejectValue("confirmPassword", "passwordsNotMatch", "Пароли не совпадают");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "emailExists", "Этот email уже зарегистрирован");
            return "registration";
        }
        userService.register(user);
        return "redirect:/login";
    }

}
