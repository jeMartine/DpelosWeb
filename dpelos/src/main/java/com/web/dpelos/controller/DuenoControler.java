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
import com.web.dpelos.service.DuenoServiceImplementation;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dueno")
public class DuenoControler {
    @Autowired
    DuenoServiceImplementation duenoService;

    @GetMapping()
    public String listaDuenos(Model model) {
        model.addAttribute("duenos", duenoService.obtenerDuenos());
        return "listaDuenos";
    }

    @GetMapping("/add")
    public String mostrarFormularioCrearDueno(Model model) {
        Dueno dueno = new Dueno();
        model.addAttribute("dueno", dueno);
        return "crearDueno";
    }

    @PostMapping("/agregar")
    public String addDueno(@ModelAttribute("dueno") Dueno dueno) {
        duenoService.addDueno(dueno);
        return "redirect:/dueno";
    }

    @GetMapping("/delete/{id}")
    public String deleteDueno(@PathVariable Long id) {
        duenoService.deleteDueno(id);
        return "redirect:/dueno";
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioActualizarDueno(Model model, @PathVariable("id") Long id) {
        model.addAttribute("dueno", duenoService.buscarDuenoPorId(id));
        return "actualizarDueno";
    }

    @PostMapping("/update/{id}")
    public String mostrarDuenoActualizado(@PathVariable("id") Long id, @ModelAttribute("dueno") Dueno dueno) {
        dueno.setIdDueno(id);
        duenoService.updateDueno(dueno);
        return "redirect:/dueno";
    }

    @GetMapping("/buscarDueno")
    public String buscarDueno() {
        return "buscarDueno";
    }

    @PostMapping("/buscarDueno")
    public String buscarDueno(@RequestParam("cedula") String cedula, HttpSession session, Model model) {
        Dueno dueno = duenoService.buscarDuenoPorCedula(cedula);
        if (dueno != null) {
            session.setAttribute("idDueno", dueno.getIdDueno());
            return "redirect:/mascota/tus-mascotas";
        } else {
            return "redirect:/login?error=true&cedula=" + cedula;
        }
    }

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
