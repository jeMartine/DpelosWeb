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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Raza;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.VeterinarioRepository;
import com.web.dpelos.service.DuenoService;
import com.web.dpelos.service.EnfermedadService;
import com.web.dpelos.service.MascotaService;
import com.web.dpelos.service.RazaService;
import com.web.dpelos.service.VeterinarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/mascota") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class MascotasController {
    @Autowired
    MascotaService mascotaService;

    @Autowired
    DuenoService duenoService;

    @Autowired
    RazaService razaService;

    @Autowired
    EnfermedadService enfermedadService;

    @Autowired
    VeterinarioService veterinarioService;

    /*
     * Metodo que retorna un JSON con un array con la informacion de las
     * mascotas registradas en la base de datos.
     */
    @GetMapping()
    public List<Mascota> listaMascotas() {
        return mascotaService.obtenerMascotas();
    }

    /*
     * No es necesario porque de llevar al usuario a la pagina se encarga el front
     * :)
     */
    // @GetMapping("/tus-mascotas")
    // public String mostrarMascotaDueno(Model model, HttpSession session) {
    // Long idDueno = (Long) session.getAttribute("idDueno");
    // if (idDueno != null) {
    // model.addAttribute("mascotas",
    // mascotaService.obtenerMascotasDelDueno(idDueno));
    // model.addAttribute("dueno", duenoService.buscarDuenoPorId(idDueno));

    // } else {
    // model.addAttribute("mascotas", Collections.emptyList());
    // model.addAttribute("dueno", null);
    // }
    // return "Dueno/inicioDueno";
    // }

    /* Metodo que retorna la informacion de una mascota segun su respectivo ID */
    @GetMapping("/{id}")
    public Mascota mostrarMascotaPorID(@PathVariable Long id) {
        return mascotaService.buscarMascotaPorId(id);
    }

    // @GetMapping("/add")
    // public String mostrarFormularioCrearMascota(Model model, HttpSession session)
    // {
    // Mascota mascota = new Mascota();
    // List<Raza> listaRazas = razaService.obtenerRazas();
    // List<Enfermedad> listaEnfermedades = enfermedadService.obtenerEnfermedades();

    // Long idVeterinario = (Long) session.getAttribute("idVeterinario");
    // if (idVeterinario != null) {
    // Veterinario veterinario = veterinarioService.buscarVetPorId(idVeterinario);
    // model.addAttribute("veterinario", veterinario);
    // }

    // model.addAttribute("mascota", mascota);
    // model.addAttribute("enfermedades", listaEnfermedades);
    // model.addAttribute("razas", listaRazas);

    // return "crearMascota";
    // }

    // @PostMapping("/agregar")
    // public String addMascota(@ModelAttribute("mascota") Mascota mascota,
    // @RequestParam("cedulaDueno") String cedulaDueno) {
    // Dueno dueno = duenoService.buscarDuenoPorCedula(cedulaDueno); // Obtener el
    // objeto Dueno desde duenoService
    // LocalDate date = LocalDate.now();
    // Date sqlDate = Date.valueOf(date);

    // if (dueno != null) {
    // mascota.setDueno(dueno); // Asignar el dueño a la mascota
    // }

    // mascota.setFechaCreacion(sqlDate);
    // mascota.setEstado(true);
    // mascotaService.addMascota(mascota);

    // return "redirect:/mascota";
    // }

    /* Metodo para agregar mascotas a la base de datos */
    @PostMapping("/agregar")
    public void addMascota(@RequestBody Mascota mascota,
            @RequestParam("cedulaDueno") String cedulaDueno) {
        Dueno dueno = duenoService.buscarDuenoPorCedula(cedulaDueno); // Obtener el objeto Dueno desde duenoService
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);

        if (dueno != null) {
            mascota.setDueno(dueno); // Asignar el dueño a la mascota
        }

        mascota.setFechaCreacion(sqlDate);
        mascota.setEstado(true);
        mascotaService.addMascota(mascota);
    }

    /* Metodo modificado para borrar una mascota usando su id */
    @DeleteMapping("/delete/{id}")
    public void deleteMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
    }

    /* No se si sea neceasrio usar este metodo, ustedes resuelvan ;D */
    @GetMapping("/update/{id}")
    public String mostrarFormularioActualizarMascota(Model model, @PathVariable Long id, HttpSession session) {
        Mascota mascota = mascotaService.buscarMascotaPorId(id);
        if (mascota != null) {
            List<Raza> listaRazas = razaService.obtenerRazas();
            List<Enfermedad> listaEnfermedades = enfermedadService.obtenerEnfermedades();

            model.addAttribute("mascota", mascota);
            model.addAttribute("enfermedades", listaEnfermedades);
            model.addAttribute("razas", listaRazas);
            Long idVet = (Long) session.getAttribute("idVeterinario");
            if (idVet != null) {
                model.addAttribute("veterinario", veterinarioService.buscarVetPorId(idVet));
            }
            return "actualizarMascota";
        } else {
            throw new NotFoundException("Mascota " + id + "no encontrada");
        }
    }

    @PutMapping("/update/{id}")
    public void mostrarMascotaActualizada(@RequestBody Mascota mascota,
            @RequestParam("dueno.cedulaDueno") String cedulaDueno) {

        Dueno dueno = duenoService.buscarDuenoPorCedula(cedulaDueno); // Obtener el objeto Dueno desde duenoService
        /* Tampoco los veo necesarios porque ya se obtienen desde el parametro. */
        // Mascota mascotaOriginal = mascotaService.buscarMascotaPorId(idMascota);
        // mascota.setFechaCreacion(mascotaOriginal.getFechaCreacion());

        if (dueno != null) {
            mascota.setDueno(dueno); // Asignar el dueño a la mascota
        } else {
            // mostrar mensaje de error
            mascota.setDueno(null);
        }
        /*
         * No es necesario porque ya se obtiene toda la info de la mascota por el
         * parametro.
         */
        // mascota.setIdMascota(idMascota);

        mascotaService.updateMascota(mascota);
        // return "redirect:/mascota";
    }

}