package com.web.dpelos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.service.EnfermedadService;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/enfermedad") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class EnfermedadController {
    @Autowired
    private EnfermedadService enfermedadService;

        /* Método para obtener todas las enfermedades */
    @GetMapping
    public ResponseEntity<List<Enfermedad>> findAll() {
        List<Enfermedad> enfermedades = enfermedadService.obtenerEnfermedades();
        if (enfermedades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Enfermedad>>(enfermedades, HttpStatus.OK);
    }

    /* Método para obtener una enfermedad por su ID */
    @GetMapping("/{id}")
    public ResponseEntity<Enfermedad> getEnfermedadById(@PathVariable Long id) {
        Enfermedad enfermedad = enfermedadService.buscarEnfermedadPorId(id);
        if (enfermedad == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
        return new ResponseEntity<Enfermedad>(enfermedad, HttpStatus.OK); 
    }

    /* Método para agregar una nueva enfermedad */
    @PostMapping
    public ResponseEntity<Enfermedad> addEnfermedad(@RequestBody Enfermedad enf) {
        Enfermedad nuevaEnfermedad = enfermedadService.addEnfermedad(enf);
        if (nuevaEnfermedad == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
        return new ResponseEntity<Enfermedad>(nuevaEnfermedad, HttpStatus.CREATED);
    }

    /* Método para actualizar una enfermedad existente */
    @PutMapping("/update")
    public ResponseEntity<Enfermedad> updateEnfermedad(@RequestBody Enfermedad enf) {
        Enfermedad actualizada = enfermedadService.updateEnfermedad(enf);
        if (actualizada == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Enfermedad>(actualizada, HttpStatus.OK);
    }

    /* Método para eliminar una enfermedad por su ID */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEnfermedad(@PathVariable Long id) {
        enfermedadService.deleteEnfermedad(id);
        return new ResponseEntity<>("Enfermedad eliminada exitosamente", HttpStatus.NO_CONTENT); 
    }


}
