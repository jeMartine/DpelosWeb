package com.web.dpelos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.web.dpelos.entity.Raza;
import com.web.dpelos.service.RazaService;


@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/raza") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)

public class RazaController {

    @Autowired
    private RazaService razaService;

    /* Metodo para agregar razas a la base de datos. */

    
    @GetMapping()
    public List<Raza> obtenerRazas() {
        return razaService.obtenerRazas();
    }

    @GetMapping("/{id}")
    public Raza getRazaById(@PathVariable Long id){
        return razaService.buscarRazaPorId(id);
    }

    @PostMapping()
    public void addRaza(@RequestBody Raza raza) {
        System.out.println("Raza recibida" + raza.getRazaMascota());
        razaService.addRaza(raza);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRaza(@PathVariable Long id){
        razaService.deleteRaza(id);
    }

    @PutMapping("/update")
    public void updateRaza(@RequestBody Raza raza){
        razaService.updateRaza(raza);
    }



}
