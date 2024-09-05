package com.web.dpelos.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.service.DuenoService;
import com.web.dpelos.service.DuenoServiceImplementation;
import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

/*Controlador de las peticiones relacionadas con la entidad Dueno */
@Controller
@RequestMapping("/dueno")
public class DuenoControler {
    @Autowired
    DuenoService duenoService;

    @Autowired
    VeterinarioService veterinarioService;

    /* Metodo que trae la lista de los duenos registrados. */
    @GetMapping()
    public String listaDuenos(Model model, HttpSession session) {
        model.addAttribute("duenos", duenoService.obtenerDuenos());
        Long idVet = (Long) session.getAttribute("idVeterinario");
        if (idVet != null) {
            model.addAttribute("veterinario", veterinarioService.buscarVetPorId(idVet));
        }
        return "listaDuenos";
    }

    /*
     * Metodo que muestra el formulario de creacion de duenos desde los permisos del
     * veterinario
     */
    @GetMapping("/add")
    public String mostrarFormularioCrearDueno(Model model, HttpSession session) {
        Dueno dueno = new Dueno();
        model.addAttribute("dueno", dueno);
        Long idVet = (Long) session.getAttribute("idVeterinario");
        if (idVet != null) {
            model.addAttribute("veterinario", veterinarioService.buscarVetPorId(idVet));
        }
        return "crearDueno";
    }

    /* Metodo que agrega un dueno a la base de datos */
    @PostMapping("/agregar")
    public String addDueno(@ModelAttribute("dueno") Dueno dueno) {
        duenoService.addDueno(dueno);
        /* Cuando el dueno se agrega, se retorna a la lista de duenos por defecto */
        return "redirect:/dueno";
    }

    /* Metodo que elimina el dueno segun el id */
    @GetMapping("/delete/{id}")
    public String deleteDueno(@PathVariable Long id) {
        duenoService.deleteDueno(id);
        return "redirect:/dueno";
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
    @PostMapping("/update/{id}")
    public String mostrarDuenoActualizado(@PathVariable("id") Long id, @ModelAttribute("dueno") Dueno dueno) {
        dueno.setIdDueno(id);
        duenoService.updateDueno(dueno);
        return "redirect:/dueno";
    }

    /* metodo que redirige al portal de buscar un dueno por su cedula */
    @GetMapping("/buscarDueno")
    public String buscarDueno() {
        return "buscarDueno";
    }

    /* Metodo que busca al dueno segun su cedula */
    @PostMapping("/buscarDueno")
    public String buscarDueno(@RequestParam("cedula") String cedula, HttpSession session, Model model) {
        Dueno dueno = duenoService.buscarDuenoPorCedula(cedula);
        /* Condicional para verificar si el dueno existe y manejar la excepcion */
        if (dueno != null) {
            session.setAttribute("idDueno", dueno.getIdDueno());
            return "redirect:/mascota/tus-mascotas";
        } else {
            return "redirect:/login?error=true&cedula=" + cedula;
        }
    }

    /*
     * Metodo que permite verificar si un dueno esta registrado consultando a traves
     * de su cedula
     */
    @GetMapping("/verificar/{cedula}")
    public ResponseEntity<Map<String, String>> verificarDueno(@PathVariable("cedula") String cedula) {
        Dueno dueno = duenoService.buscarDuenoPorCedula(cedula);
        Map<String, String> response = new HashMap<>();

        if (dueno != null) {
            response.put("existe", "true");
            response.put("nombre", dueno.getNombreDueno() + " " + dueno.getApellidoDueno());
        } else {
            response.put("existe", "false");
        }

        return ResponseEntity.ok(response);
    }

}
