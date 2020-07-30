package ru.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.models.User;
import ru.web.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/myInfo")
    public String getUsers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("authUsername", user.getUsername());
        model.addAttribute("authLastname", user.getLastname());
        model.addAttribute("authRole", user.getRoles());
        model.addAttribute("authID", user.getId());
        model.addAttribute("authEmail", user.getEmail());
        model.addAttribute("authAge", user.getAge());
        return "myinfo";
    }
}
