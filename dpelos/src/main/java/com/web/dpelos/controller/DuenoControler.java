package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.service.DuenoServiceImplementation;

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
    public String mostrarFormularioActualizarDueno(Model model, @PathVariable Long id) {
        model.addAttribute("dueno", duenoService.buscarDuenoPorId(id));
        return "actualizarDueno";
    }

    @PostMapping("/update/{id}")
    public String mostrarDuenoActualizado(@ModelAttribute("dueno") Dueno dueno) {
        duenoService.updateDueno(dueno);
        return "redirect:/dueno";
    }
}
