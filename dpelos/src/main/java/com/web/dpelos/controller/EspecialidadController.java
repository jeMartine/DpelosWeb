package com.web.dpelos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dpelos.entity.Especialidad;
import com.web.dpelos.service.EspecialidadService;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/especialidad") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    /* Metodo que obtiene todas las especialidades */
    @GetMapping
    public ResponseEntity< List<Especialidad>> findAll(){
        List<Especialidad> esp = especialidadService.obtenerEspecialidades();
        if(esp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Especialidad>>(esp, HttpStatus.OK);
    }
    
}
