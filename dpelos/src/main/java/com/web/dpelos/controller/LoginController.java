package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dpelos.repository.VeterinarioRepository;
import com.web.dpelos.service.DuenoServiceImplementation;
import com.web.dpelos.service.VeterinarioService;
import com.web.dpelos.service.VeterinarioServiceImplentation;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    DuenoServiceImplementation duenoService;

    @Autowired
    VeterinarioService veterinarioService;

    @GetMapping
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "cedula", required = false) String cedula,
                        @RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "userType", required = false) String userType,
                        Model model) {
        if (error != null) {
            String mensajeError = "";
            if ("veterinario".equals(userType)) {
                if ("password".equals(error)) {
                    mensajeError = "Contraseña incorrecta para el veterinario con cédula " + cedula;
                }else{
                    mensajeError = cedula != null
                        ? "No se encontró un veterinario con la cédula " + cedula
                        : "No se encontró un veterinario";
                }
                model.addAttribute("mostrarPopupVeterinario", true);
            } else {
                mensajeError = cedula != null
                        ? "No se encontró un dueño con la cédula " + cedula
                        : "No se encontró un dueño";
                model.addAttribute("mostrarPopupCliente", true);
            }
            model.addAttribute("mensajeError", mensajeError);
        }
        return "login";
    }

}
