package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.dto.LoginRequest;
import com.web.dpelos.service.DuenoServiceImplementation;
import com.web.dpelos.service.VeterinarioService;


@Controller
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    DuenoServiceImplementation duenoService;

    @Autowired
    VeterinarioService veterinarioService;


    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequest peticion) {
        Object user = null;

        if (peticion.getType() == 1) {
            user = veterinarioService.buscarVetLogin(peticion.getDocument(), peticion.getPassword());
        } else if (peticion.getType() == 2) {
            user = duenoService.buscarDuenoPorCedula(peticion.getDocument());
        }

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            String errorMessage = peticion.getType() == 1 ? "Usuario o credenciales incorrectas" : "No existe el dueño";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }


    // @GetMapping
    // public String login(@RequestParam(value = "error", required = false) String error,
    //                     @RequestParam(value = "cedula", required = false) String cedula,
    //                     @RequestParam(value = "password", required = false) String password,
    //                     @RequestParam(value = "userType", required = false) String userType,
    //                     Model model) {
    //     if (error != null) {
    //         String mensajeError = "";
    //         if ("veterinario".equals(userType)) {
    //             if ("password".equals(error)) {
    //                 mensajeError = "Contraseña incorrecta para el veterinario con cédula " + cedula;
    //             }else{
    //                 mensajeError = cedula != null
    //                     ? "No se encontró un veterinario con la cédula " + cedula
    //                     : "No se encontró un veterinario";
    //             }
    //             model.addAttribute("mostrarPopupVeterinario", true);
    //         } else {
    //             mensajeError = cedula != null
    //                     ? "No se encontró un dueño con la cédula " + cedula
    //                     : "No se encontró un dueño";
    //             model.addAttribute("mostrarPopupCliente", true);
    //         }
    //         model.addAttribute("mensajeError", mensajeError);
    //     }
    //     return "login";
    // }

}
