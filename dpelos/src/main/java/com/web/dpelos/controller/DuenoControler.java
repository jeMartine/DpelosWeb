package com.web.dpelos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.service.DuenoService;
import com.web.dpelos.service.DuenoServiceImplementation;
import com.web.dpelos.service.VeterinarioService;


import jakarta.servlet.http.HttpSession;

/*Controlador de las peticiones relacionadas con la entidad Dueno */
@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/dueno")
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class DuenoControler {
    @Autowired
    DuenoService duenoService;

    @Autowired
    VeterinarioService veterinarioService;

    /*
     * Metodo que retorna un array dentor de un JSON que contiene los duenos
     * registrados.
     */
    @GetMapping()
    public List<Dueno> listaDuenos(HttpSession session) {
        return duenoService.obtenerDuenos();
    }

    /* Metodo que agrega un dueno a la base de datos */
    @PostMapping()
    public void addDueno(@RequestBody Dueno dueno) {

        duenoService.addDueno(dueno);
    }

    /* Metodo que elimina el dueno segun el id */
    @DeleteMapping("/delete/{id}")
    public void deleteDueno(@PathVariable Long id) {
        duenoService.deleteDueno(id);
    }

    /* Metodo que actualiza la informacion del dueno segun el id */
    @GetMapping("/update/{id}")
    public String mostrarFormularioActualizarDueno(Model model, @PathVariable("id") Long id, HttpSession session) {
        model.addAttribute("dueno", duenoService.buscarDuenoPorId(id));
        Long idVet = (Long) session.getAttribute("idVeterinario");
        if (idVet != null) {
            model.addAttribute("veterinario", veterinarioService.buscarVetPorId(idVet));
        }
        return "actualizarDueno";
    }

    /* Metodo para mostrar el perfil de dueno actualizado */
    @PutMapping("/update/{id}")
    public void mostrarDuenoActualizado(@RequestBody Dueno dueno) {
        duenoService.updateDueno(dueno);
        // return "redirect:/dueno";
    }

    /* Este metodo lo modifique para que retornara true si encuetra un dueno */
    /* Metodo que busca al dueno segun su cedula */
    @GetMapping("/buscarCedula/{cedula}")
    public Dueno buscarDueno(@PathVariable("cedula") String cedula, HttpSession session) {
        return duenoService.buscarDuenoPorCedula(cedula);
    }

}
