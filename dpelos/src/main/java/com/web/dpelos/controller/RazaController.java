package com.web.dpelos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Raza;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.RazaService;
import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/raza")
public class RazaController {

    @Autowired
    private RazaService razaService;

    @PostMapping("/agregar")
    public String agregarRaza(@RequestParam("nombreRaza") String nombreRaza) {
        Raza raza = new Raza();
        raza.setRazaMascota(nombreRaza);
        razaService.addRaza(raza);
        return "redirect:/registros"; 
    }

}
