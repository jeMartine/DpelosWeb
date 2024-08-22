package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.service.DuenoServiceImplementation;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    DuenoServiceImplementation duenoService;
    
    @GetMapping()
    public String login() {
        return "login";
    }

}
