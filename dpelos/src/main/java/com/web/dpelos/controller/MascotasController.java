package com.web.dpelos.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.web.dpelos.entity.Droga;
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

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    /* Metodo que retorna la informacion de una mascota segun su respectivo ID */
    @GetMapping("/{id}")
    public Mascota mostrarMascotaPorID(@PathVariable Long id) {
        return mascotaService.buscarMascotaPorId(id);
    }

    @GetMapping("/dueno/{id}")
    public ResponseEntity<?> mascotasDueno(@PathVariable Long id) {
        List<Mascota> mascotas = mascotaService.obtenerMascotasDelDueno(id);
        if (!mascotas.isEmpty()) {
            return ResponseEntity.ok(mascotas);
        } else {
            String errorMessage = "No se encontraron mascotas";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
    /* Metodo para agregar mascotas a la base de datos */
    @PostMapping("/add")
    public void addMascota(@RequestBody Mascota mascota) {
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);

        mascota.setFechaCreacion(sqlDate);
        mascotaService.addMascota(mascota);
    }

    /* Metodo modificado para borrar una mascota usando su id */
    @DeleteMapping("/delete/{id}")
    public void deleteMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
    }
    
    @PutMapping("/update")
    public void actualizarMascota(@RequestBody Mascota mascota) {
        mascotaService.updateMascota(mascota);
    }

    @GetMapping("/buscar")
    public List<Mascota> buscarMascotas(@RequestParam String nombre) {
        return mascotaService.buscarMascotasPorNombre(nombre);
    }

}