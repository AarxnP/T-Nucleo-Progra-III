package com.desarrollo.complexivo_app.controllers;

import com.desarrollo.complexivo_app.models.User;
import com.desarrollo.complexivo_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/register")
    public String getFormRegister(Model model) {
        model.addAttribute("user", new User());
        return "auth/register"; //template
    }

    @PostMapping("/register")
    public RedirectView registerUser(@ModelAttribute User user) {
        try {
            this.service.save(user);
            return new RedirectView("/login");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new RedirectView("/register?duplicate");
        }
    }

}
