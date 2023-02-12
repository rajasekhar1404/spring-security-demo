package com.security.controller;

import com.security.entity.User;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String getHome() {
        return "Welcome to home..!";
    }

    @GetMapping("/home")
    public String getMainPage() {
        return "Welcome User..!";
    }

    @GetMapping("/admin")
    public String adminOnly() {
        return "Hello admin";
    }

    @GetMapping("/admin-meth")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String methodForAdmin() {
        return "Welcome to admin method.";
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws Exception {
        System.out.println(user);
        return userService.registerUser(user);
    }

}
