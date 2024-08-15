package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.dpelos.service.MascotaServiceImplementation;



@Controller
@RequestMapping("/mascota")
public class MascotasController {
    @Autowired
    MascotaServiceImplementation mascotaService;
    
    @GetMapping()
    public String listaMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.obtenerMascotas());
        return "listaMascotas";
    }
    @GetMapping("/{id}")
    public String mostrarMascotaPorID(Model model, @PathVariable Integer id) {
        model.addAttribute("mascota", mascotaService.buscarMascotaPorId(id));
        return "mascotaPorID";
    }
}
