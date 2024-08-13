package com.web.dpelos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/petslist")
public class PetsListController {
    @GetMapping
    public String petsList() {
        return "petsList";
    }
    
    
}
