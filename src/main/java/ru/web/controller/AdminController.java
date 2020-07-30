package ru.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.models.Role;
import ru.web.models.User;
import ru.web.services.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/panel")
    public String getUsers(Model model) {
        model.addAttribute("userlist", userService.getAllUsers());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("authUsername", user.getUsername());
        model.addAttribute("authLastname", user.getLastname());
        model.addAttribute("authRole", user.getRoles());
        model.addAttribute("authID", user.getId());
        model.addAttribute("authEmail", user.getEmail());
        model.addAttribute("authAge", user.getAge());
        return "admin/panel";
    }

    @PostMapping("/panel/addUser")
    public String addUser(@RequestParam(name = "username") String username,@RequestParam(name = "lastname") String lastname,@RequestParam(name = "age") byte age, @RequestParam(name = "email") String email,
                          @RequestParam(name = "password") String password, @RequestParam(value = "role", required = true) Role role, Model model) {
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setLastname(lastname);
        user.setAge(age);
        model.addAttribute("roleadmin", Role.ROLE_ADMIN.getAuthority());
        model.addAttribute("roleuser", Role.ROLE_USER);
        Set<Role> set = new HashSet<Role>();
        if (role != null) {
            if (role.getAuthority().equals(Role.ROLE_ADMIN.getAuthority())) {
                set.add(Role.ROLE_ADMIN);
                set.add(Role.ROLE_USER);
            }
        }
        if (role != null) {
            if (role.getAuthority().equals(Role.ROLE_USER.getAuthority())) {
                set.add(Role.ROLE_USER);
            }
        }
        user.setRoles(set);
        userService.addUser(user);
        return "redirect:/admin/panel";
    }

    @PostMapping("/panel/deleteUser")
    public String deleteUser(@RequestParam("id") Long id, Model model) {

        model.addAttribute("userID", userService.findOne(id).getId());
        model.addAttribute("firstname", userService.findOne(id).getUsername());
        model.addAttribute("lastname", userService.findOne(id).getLastname());
        model.addAttribute("age", userService.findOne(id).getAge());
        model.addAttribute("email", userService.findOne(id).getEmail());
        model.addAttribute("roles", userService.findOne(id).getAuthorities());
        userService.deleteById(id);;
        return "redirect:/admin/panel";
    }

    @PostMapping("/panel/editUser")
    public String editForm(@RequestParam("id") Long id,@RequestParam(name = "username") String username, @RequestParam(name = "email") String email,
                           @RequestParam(name = "password") String password, @RequestParam(value = "role", required = false) Role role, Model model) {
        model.addAttribute("id", id);
        if (id != 0 & id >= userService.getAllUsers().size()) {
            User exsistUser = userService.findOne(id);
            exsistUser.setName(username);
            exsistUser.setEmail(email);
            exsistUser.setPassword(password);
            model.addAttribute("admin", Role.ROLE_ADMIN);
            model.addAttribute("user", Role.ROLE_ADMIN);
            model.addAttribute("id", exsistUser.getId());
            Set<Role> set = new HashSet<Role>();
            if (role != null) {
                if (role.getAuthority().equals(Role.ROLE_ADMIN.getAuthority())) {
                    set.add(Role.ROLE_ADMIN);
                    set.add(Role.ROLE_USER);
                }
            }
            if (role != null) {
                if (role.getAuthority().equals(Role.ROLE_USER.getAuthority())) {
                    set.add(Role.ROLE_USER);
                }
            }
            exsistUser.setRoles(set);
            userService.edit(exsistUser);
        }
        return "redirect:/admin/panel";
    }
}