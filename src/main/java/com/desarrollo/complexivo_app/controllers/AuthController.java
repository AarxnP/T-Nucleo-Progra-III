package com.desarrollo.complexivo_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String getFormLogin(){
        return "auth/login";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
