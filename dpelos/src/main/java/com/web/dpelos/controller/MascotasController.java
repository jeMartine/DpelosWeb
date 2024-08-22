package com.web.dpelos.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.dpelos.entity.Mascota;
import com.web.dpelos.service.MascotaServiceImplementation;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/mascota")
public class MascotasController {
    @Autowired
    MascotaServiceImplementation mascotaService;

    @GetMapping()
    public String listaMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.obtenerMascotas());
        return "listaMascotas";
    }

    @GetMapping("/tus-mascotas")
    public String mostrarMascotaDueno(Model model, HttpSession session) {
        Long idDueno = (Long) session.getAttribute("idDueno");
        if (idDueno != null) {
            model.addAttribute("mascotas", mascotaService.obtenerMascotasDelDueno(idDueno));
        } else {
            model.addAttribute("mascotas", Collections.emptyList());
        }
        return "Dueno/inicioDueno";
    }

    @GetMapping("/{id}")
    public String mostrarMascotaPorID(Model model, @PathVariable Long id) {
        model.addAttribute("mascota", mascotaService.buscarMascotaPorId(id));
        return "mascotaPorID";
    }

    @GetMapping("/add")
    public String mostrarFormularioCrearMascota(Model model) {
        Mascota mascota = new Mascota();
        model.addAttribute("mascota", mascota);
        return "crearMascota";
    }

    @PostMapping("/agregar")
    public String addMascota(@ModelAttribute("mascota") Mascota mascota) {
        mascotaService.addMascota(mascota);

        return "redirect:/mascota";
    }

    @GetMapping("/delete/{id}")
    public String deleteMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
        return "redirect:/mascota";
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioActualizarMascota(Model model, @PathVariable Long id) {
        model.addAttribute("mascota", mascotaService.buscarMascotaPorId(id));
        return "actualizarMascota";
    }

    @PostMapping("/update/{id}")
    public String mostrarMascotaActualizada(@ModelAttribute("mascota") Mascota mascota) {
        mascotaService.updateMascota(mascota);
        return "redirect:/mascota";
    }

}
