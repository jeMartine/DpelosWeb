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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.service.DuenoService;
import com.web.dpelos.service.DuenoServiceImplementation;
import com.web.dpelos.service.VeterinarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
        // model.addAttribute("duenos", duenoService.obtenerDuenos());
        // Long idVet = (Long) session.getAttribute("idVeterinario");
        // if (idVet != null) {
        // model.addAttribute("veterinario", veterinarioService.buscarVetPorId(idVet));
        // }
        return duenoService.obtenerDuenos();
    }

    /*
     * Metodo que muestra el formulario de creacion de duenos desde los permisos del
     * veterinario
     */
    // @GetMapping("/add")
    // public String mostrarFormularioCrearDueno(Model model, HttpSession session) {
    // Dueno dueno = new Dueno();
    // model.addAttribute("dueno", dueno);
    // Long idVet = (Long) session.getAttribute("idVeterinario");
    // if (idVet != null) {
    // model.addAttribute("veterinario", veterinarioService.buscarVetPorId(idVet));
    // }
    // return "crearDueno";
    // }

    /* Metodo que agrega un dueno a la base de datos */
    @PostMapping("/agregar")
    public void addDueno(@RequestBody Dueno dueno) {
        duenoService.addDueno(dueno);
        // /* Cuando el dueno se agrega, se retorna a la lista de duenos por defecto */
        // return "redirect:/dueno";
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

    // /* metodo que redirige al portal de buscar un dueno por su cedula */
    // @GetMapping("/buscarDueno")
    // public String buscarDueno() {
    // return "buscarDueno";
    // }

    /* Este metodo lo modifique para que retornara true si encuetra un dueno */
    /* Metodo que busca al dueno segun su cedula */
    @PostMapping("/buscarDueno")
    public boolean buscarDueno(@RequestParam("cedula") String cedula, HttpSession session) {
        Dueno dueno = duenoService.buscarDuenoPorCedula(cedula);
        /* Condicional para verificar si el dueno existe y manejar la excepcion */
        if (dueno != null) {
            session.setAttribute("idDueno", dueno.getIdDueno());
            return true;
        } else {
            return false;
        }
    }

    /* No entiendo como deberia modificar este metedo. */
    // /*
    // * Metodo que permite verificar si un dueno esta registrado consultando a
    // traves
    // * de su cedula
    // */
    // @GetMapping("/verificar/{cedula}")
    // public ResponseEntity<Map<String, String>>
    // verificarDueno(@PathVariable("cedula") String cedula) {
    // Dueno dueno = duenoService.buscarDuenoPorCedula(cedula);
    // Map<String, String> response = new HashMap<>();

    // if (dueno != null) {
    // response.put("existe", "true");
    // response.put("nombre", dueno.getNombreDueno() + " " +
    // dueno.getApellidoDueno());
    // } else {
    // response.put("existe", "false");
    // }

    // return ResponseEntity.ok(response);
    // }

}
