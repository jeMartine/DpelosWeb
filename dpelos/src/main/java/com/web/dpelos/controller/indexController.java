package com.web.dpelos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Realmente necesitamos esto dado que la pagina ahora la carga Angular? */
@RestController
@RequestMapping()
<<<<<<< HEAD
public class indexController {
=======
public class indexController{
>>>>>>> main
    public String vistaPrincipal() {
        return "index";
    }
}
