package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.EnfermedadService;
import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/enfermedad")
public class EnfermedadController {
    @Autowired
    private EnfermedadService enfermedadService;

    @PostMapping("/agregar")
    public String agregarEnfermedad(@RequestParam("nombreEnfermedad") String nombreEnfermedad) {
        Enfermedad enfermedad = new Enfermedad();
        enfermedad.setNombreEnfermedad(nombreEnfermedad);
        enfermedadService.addEnfermedad(enfermedad);
        return "redirect:/registros";
    }
    
}
