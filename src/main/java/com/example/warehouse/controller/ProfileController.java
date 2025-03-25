package com.example.warehouse.controller;

import com.example.warehouse.entity.User;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public String getProfile(Principal principal, Model model) {
        User user = userService.getUser(principal);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(User user, Model model) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent() && !userOptional.get().getId().equals(user.getId())) {
            model.addAttribute("emailExists", "Этот email уже зарегистрирован");
            model.addAttribute("user",userOptional.get());
            return "profile";
        }
        userService.update(user);
        return "redirect:/profile";
    }
}
