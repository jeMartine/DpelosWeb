package com.web.dpelos.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Raza;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.service.DuenoService;
import com.web.dpelos.service.EnfermedadService;
import com.web.dpelos.service.MascotaService;
import com.web.dpelos.service.RazaService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/mascota")
public class MascotasController {
    @Autowired
    MascotaService mascotaService;

    @Autowired
    DuenoService duenoService;

    @Autowired
    RazaService razaService;

    @Autowired
    EnfermedadService enfermedadService;

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
        model.addAttribute("dueno", duenoService.buscarDuenoPorId(idDueno));
        
    } else {
        model.addAttribute("mascotas", Collections.emptyList());
        model.addAttribute("dueno", null);
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
        List <Raza> listaRazas = razaService.obtenerRazas();
        List <Enfermedad> listaEnfermedades = enfermedadService.obtenerEnfermedades();

        model.addAttribute("mascota", mascota);
        model.addAttribute("enfermedades", listaEnfermedades);
        model.addAttribute("razas", listaRazas);
        
        return "crearMascota";
    }

    @PostMapping("/agregar")
    public String addMascota(@ModelAttribute("mascota") Mascota mascota, @RequestParam("cedulaDueno") String cedulaDueno) {
        Dueno dueno = duenoService.buscarDuenoPorCedula(cedulaDueno); // Obtener el objeto Dueno desde duenoService
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);

        if (dueno != null) {
            mascota.setDueno(dueno); // Asignar el dueño a la mascota
        }

        mascota.setFechaCreacion(sqlDate);
        mascota.setEstado(true);
        mascotaService.addMascota(mascota);

        return "redirect:/mascota";
    }

    @GetMapping("/delete/{id}")
    public String deleteMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
        return "redirect:/mascota";
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioActualizarMascota(Model model, @PathVariable Long id ) {
        Mascota mascota = mascotaService.buscarMascotaPorId(id);
    
        if(mascota!=null){
            List <Raza> listaRazas = razaService.obtenerRazas();
            List <Enfermedad> listaEnfermedades = enfermedadService.obtenerEnfermedades();
    
            model.addAttribute("mascota", mascota);
            model.addAttribute("enfermedades", listaEnfermedades);
            model.addAttribute("razas", listaRazas);

            return "actualizarMascota";
        }else{
            throw new NotFoundException("No Se encuentra la mascota con id "+ id);
        }
        
    }

    @PostMapping("/update/{id}")
    public String mostrarMascotaActualizada(@ModelAttribute("mascota") Mascota mascota, @PathVariable("id") Long idMascota, @RequestParam("dueno.cedulaDueno") String cedulaDueno) {
        
        Dueno dueno = duenoService.buscarDuenoPorCedula(cedulaDueno); // Obtener el objeto Dueno desde duenoService

        if (dueno != null) {
            mascota.setDueno(dueno); // Asignar el dueño a la mascota
        }else{
            //mostrar mensaje de error
            mascota.setDueno(null);
        }   

        mascota.setIdMascota(idMascota);
       
        mascotaService.updateMascota(mascota);
        return "redirect:/mascota";
    }

}