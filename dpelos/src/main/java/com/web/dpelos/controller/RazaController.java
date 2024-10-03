package com.web.dpelos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Raza;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.RazaService;
import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/raza") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)

public class RazaController {

    @Autowired
    private RazaService razaService;

    /* Metodo para agregar razas a la base de datos. */
    @PutMapping("/agregar")
    public void agregarRaza(@RequestParam("nombreRaza") String nombreRaza) {
        Raza raza = new Raza();
        raza.setRazaMascota(nombreRaza);
        razaService.addRaza(raza);
    }

    @GetMapping()
    public List<Raza> obtenerRazas() {
        return razaService.obtenerRazas();
    }

}
