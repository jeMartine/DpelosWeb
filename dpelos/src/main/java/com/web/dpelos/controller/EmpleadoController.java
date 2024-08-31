package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.repository.VeterinarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    VeterinarioRepository veterinarioRepository;
    
    @GetMapping
    public String indexEmpleado() {
        return "empleado/indexEmpleado";
    }
    
}
