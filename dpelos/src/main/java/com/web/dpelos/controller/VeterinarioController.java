package com.web.dpelos.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/vet") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class VeterinarioController {
    @Autowired
    VeterinarioService veterinarioService;

    //Retornar todos los veterinarios
    @GetMapping
    public List<Veterinario> findAll(){
        return veterinarioService.findAll();
    }

    //Retornar un veterinario por su id
    @GetMapping("/{id}")
    public Veterinario findVetById(@PathVariable Long id){
        return veterinarioService.buscarVetPorId(id);
    }

    //Crear un veterinario
    @PostMapping
    public void crearVet(@RequestBody Veterinario vet){
        veterinarioService.addVet(vet);
    }

    //Eliminar un veterinario por su id
    @DeleteMapping("/delete/{id}")
    public void deleteVet(@PathVariable Long id){
        veterinarioService.deleteVet(id);
    }

    @PutMapping("/update")
    public void updateVet(@RequestBody Veterinario vet){
        veterinarioService.updateVet(vet);
    }

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
