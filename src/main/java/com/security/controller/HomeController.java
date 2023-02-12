package com.security.controller;

import com.security.entity.Login;
import com.security.entity.User;
import com.security.service.JwtService;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

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

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody Login login) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        if(authenticate.isAuthenticated()) {
            return jwtService.generateToken(login.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }

    }

}
