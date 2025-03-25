package com.example.warehouse.controller;

import com.example.warehouse.entity.User;
import com.example.warehouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUserPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/ban/{userId}")
    public String bunUser(@PathVariable Long userId, RedirectAttributes redirectAttributes, Principal principal) {
        User user = userService.getUser(principal);
        if(user.getId().equals(userId)) {
            redirectAttributes.addFlashAttribute("error", "Нельзя забанить себя!");
            return "redirect:/users";
        }
        userService.banUser(userId);
        return "redirect:/users";
    }

    @PostMapping("/manager/{userId}")
    public String madeManager(@PathVariable Long userId, RedirectAttributes redirectAttributes, Principal principal) {
        User user = userService.getUser(principal);
        if(user.getId().equals(userId)) {
            redirectAttributes.addFlashAttribute("error", "Нельзя сделать менеджером себя!");
            return "redirect:/users";
        }
        userService.madeManagerUser(userId);
        return "redirect:/users";
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttributes, Principal principal) {
        User user = userService.getUser(principal);
        if(user.getId().equals(userId)) {
            redirectAttributes.addFlashAttribute("error", "Нельзя удалить себя!");
            return "redirect:/users";
        }
        userService.delete(userId);
        return "redirect:/users";
    }

}
