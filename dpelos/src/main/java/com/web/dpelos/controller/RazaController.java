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

import com.web.dpelos.entity.Raza;
import com.web.dpelos.service.RazaService;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/raza") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://dpelos.site") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                             // origen diferente (Angular)

public class RazaController {

    @Autowired
    private RazaService razaService;

    /* Método para obtener todas las razas */
    @GetMapping()
    public ResponseEntity<List<Raza>> obtenerRazas() {
        List<Raza> razas = razaService.obtenerRazas();
        if (razas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Raza>>(razas, HttpStatus.OK);
    }

    /* Método para obtener una raza por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Raza> getRazaById(@PathVariable Long id) {
        Raza raza = razaService.buscarRazaPorId(id);
        if (raza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Raza>(raza, HttpStatus.OK);
    }

    /* Método para agregar una raza a la base de datos */
    @PostMapping()
    public ResponseEntity<Raza> addRaza(@RequestBody Raza raza) {

        Raza nuevaRaza = razaService.addRaza(raza);
        if (nuevaRaza == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Raza>(nuevaRaza, HttpStatus.CREATED);
    }

    /* Método para eliminar una raza por ID */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRaza(@PathVariable Long id) {
        // boolean isDeleted = razaService.deleteRaza(id);
        razaService.deleteRaza(id);
        return new ResponseEntity<>("Raza eliminada.", HttpStatus.OK);

        // if (!isDeleted) {
        // return new ResponseEntity<>("Raza no encontrada.", HttpStatus.NOT_FOUND);
        // }
    }

    /* Método para actualizar una raza */
    @PutMapping("/update")
    public ResponseEntity<Raza> updateRaza(@RequestBody Raza raza) {
        Raza nuevRaza = razaService.updateRaza(raza);
        if (nuevRaza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Raza>(HttpStatus.OK);
    }

}
