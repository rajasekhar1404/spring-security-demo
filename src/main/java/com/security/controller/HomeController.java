package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

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

}
