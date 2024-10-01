package com.web.dpelos.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/vet") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class VeterinarioController {
    @Autowired
    VeterinarioService veterinarioService;

    /* Metodo que retorna el HTML con la pagina del vet. */
    // @GetMapping("/buscarVet")
    // public String buscarVeterinario() {
    // return "buscarVet";
    // }

    /* Metodo que retorna un JSON con la informacion del vet. */
    @GetMapping("/buscarVet")
    public Veterinario buscarVeterinario(@RequestParam("cedulaVet") String cedulaVet,
            @RequestParam("passwordVet") String passwordVet,
            HttpSession session) {
        Veterinario veterinario = veterinarioService.buscarVetLogin(cedulaVet, passwordVet);
        if (veterinario != null) {
            session.setAttribute("idVeterinario", veterinario.getIdVeterinario());
            return veterinario;
        } else {
            Veterinario vetExiste = veterinarioService.buscarVetPorCedula(cedulaVet);
            if (vetExiste != null) {
                return vetExiste;
            } else {
                return vetExiste;
            }
        }
    }

}
