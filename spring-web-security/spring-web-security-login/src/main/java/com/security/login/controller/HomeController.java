package com.security.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home page";
    }

    @GetMapping("/login")
    public String login() {
        return "login ok";
    }
}
