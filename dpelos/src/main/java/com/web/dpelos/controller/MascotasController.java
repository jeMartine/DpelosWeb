package com.web.dpelos.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.dpelos.entity.Mascota;
import com.web.dpelos.service.DuenoService;
import com.web.dpelos.service.EnfermedadService;
import com.web.dpelos.service.MascotaService;
import com.web.dpelos.service.RazaService;
import com.web.dpelos.service.VeterinarioService;

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
    public ResponseEntity<List<Mascota>> listaMascotas() {
        List<Mascota> mascotas = mascotaService.obtenerMascotas();
        if (mascotas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }
    
    /* Metodo que retorna la informacion de una mascota segun su respectivo ID */
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> mostrarMascotaPorID(@PathVariable Long id) {
        Mascota mascota = mascotaService.buscarMascotaPorId(id);
        if (mascota == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mascota, HttpStatus.OK);
    }

    @GetMapping("/dueno/{id}")
    public ResponseEntity<List<Mascota>> mascotasDueno(@PathVariable Long id) {
        List<Mascota> mascotas = mascotaService.obtenerMascotasDelDueno(id);
        if (mascotas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }

    /* Metodo para agregar mascotas a la base de datos */
    @PostMapping("/add")
    public ResponseEntity<Mascota> addMascota(@RequestBody Mascota mascota) {
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);

        mascota.setFechaCreacion(sqlDate);
        Mascota masc = mascotaService.addMascota(mascota);

        if(masc==null){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<Mascota>(masc, HttpStatus.OK);
    }

    /* Metodo modificado para borrar una mascota usando su id */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
        return new ResponseEntity<String>("Mascota eliminada", HttpStatus.NO_CONTENT);
    }

    /* Metodo para actualizar la informacion de una mascota */
    @PutMapping("/update")
    public ResponseEntity<Mascota> actualizarMascota(@RequestBody Mascota mascota) {
        Mascota masc =  mascotaService.updateMascota(mascota);
        if(masc == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Mascota>(masc, HttpStatus.OK);
    }

    /* Metodo para buscar mascotas por su nombre */
    @GetMapping("/buscar")
    public ResponseEntity<Page<Mascota>> buscarMascotas(
            @RequestParam String nombre,
            @RequestParam int page,
            @RequestParam int size) {

        Page<Mascota> pgMasc = mascotaService.buscarMascotasPorNombre(nombre, page, size);
        if(pgMasc.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Mascota>>(pgMasc, HttpStatus.OK);
    }

    /* Método para obtener todas las mascotas con paginación */
    @GetMapping("/paginacion")
    public ResponseEntity<Page<Mascota>> getMascotasPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        Page<Mascota> mascotasPaginadas = mascotaService.getMascotasPaginadas(page, size);
        return new ResponseEntity<>(mascotasPaginadas, HttpStatus.OK);
    }

    /* Método para obtener el total de mascotas */
    @GetMapping("/total")
    public ResponseEntity<Long> obtenerTotalMascotas() {
        long totalMascotas = mascotaService.obtenerTotalMascotas();
        return new ResponseEntity<>(totalMascotas, HttpStatus.OK);
    }

    /* Método para obtener el total de mascotas activas */
    @GetMapping("/totalActivas")
    public ResponseEntity<Long> obtenerTotalMascotasActivas() {
        long totalMascotasActivas = mascotaService.obtenerTotalMascotasActivas();
        return new ResponseEntity<>(totalMascotasActivas, HttpStatus.OK);
    }
}