package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    VeterinarioService veterinarioService;
    
    //carga el html con base al empleado que inició sesion
    @GetMapping("/vet")
    public String mostrarMascotaDueno(Model model, HttpSession session) {
        Long idVet = (Long) session.getAttribute("idVeterinario");
    if (idVet != null) {
        model.addAttribute("veterinario", veterinarioService.buscarVetPorId(idVet));
    } else {
        model.addAttribute("veterinario", null);
    }
        return "empleado/indexEmpleado";
    }
    
}
