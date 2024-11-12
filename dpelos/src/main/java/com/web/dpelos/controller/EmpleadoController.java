package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/empleado") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://dpelos.site") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                             // origen diferente (Angular)

public class EmpleadoController {
    @Autowired
    VeterinarioService veterinarioService;

    // Retorna un JSON con la info del empleado
    @GetMapping("/vet")
    public Veterinario mostrarVeterinario(HttpSession session) {
        Long idVet = (Long) session.getAttribute("idVeterinario");
        return veterinarioService.buscarVetPorId(idVet);
    }

}
