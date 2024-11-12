package com.web.dpelos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.UserEntity;
import com.web.dpelos.repository.UserRepository;
import com.web.dpelos.security.CustomUserDetailService;
import com.web.dpelos.service.DuenoService;

import jakarta.servlet.http.HttpSession;

/*Controlador de las peticiones relacionadas con la entidad Dueno */
@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/dueno")
@CrossOrigin(origins = "http://dpelos.site") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                             // origen diferente (Angular)
public class DuenoController {
    @Autowired
    DuenoService duenoService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserDetailService customUserDetailService;

    /* Método que retorna la lista de dueños registrados */
    @GetMapping()
    public ResponseEntity<List<Dueno>> listaDuenos() {
        List<Dueno> duenos = duenoService.obtenerDuenos();
        if (duenos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Dueno>>(duenos, HttpStatus.OK);
    }

    /* Retorna un dueño por su id */
    @GetMapping("/{id}")
    public ResponseEntity<Dueno> getDuenoById(@PathVariable Long id) {
        Dueno dueno = duenoService.buscarDuenoPorId(id);
        if (dueno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Dueno>(dueno, HttpStatus.OK);
    }

    /* Método que agrega un dueño a la base de datos */
    @PostMapping()
    public ResponseEntity addDueno(@RequestBody Dueno dueno) {
        /*
         * Dueno nuevoDueno = duenoService.addDueno(dueno);
         * if (nuevoDueno == null) {
         * return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         * }
         * return new ResponseEntity<Dueno>(nuevoDueno, HttpStatus.CREATED);
         */
        if (userRepository.existsByUsername(dueno.getCedulaDueno())) {
            return new ResponseEntity<String>("Este dueño ya existe", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = customUserDetailService.duenoToUser(dueno);
        dueno.setUser(userEntity);
        Dueno nuevoDueno = duenoService.addDueno(dueno);
        if (nuevoDueno == null) {
            return new ResponseEntity<Dueno>(nuevoDueno, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Dueno>(nuevoDueno, HttpStatus.CREATED);
    }

    /* Método que elimina el dueño según su id */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDueno(@PathVariable Long id) {
        duenoService.deleteDueno(id);
        return new ResponseEntity<>("Dueño eliminado exitosamente", HttpStatus.NO_CONTENT);
    }

    /* Método para actualizar la información del dueño */
    @PutMapping("/update")
    public ResponseEntity<Dueno> mostrarDuenoActualizado(@RequestBody Dueno dueno) {
        Dueno duenoActualizado = duenoService.updateDueno(dueno);
        if (duenoActualizado == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Dueno>(duenoActualizado, HttpStatus.OK);
    }

    /* Método que busca al dueño según su cédula */
    @GetMapping("/buscarCedula/{cedula}")
    public ResponseEntity<Dueno> buscarDueno(@PathVariable("cedula") String cedula, HttpSession session) {
        Dueno dueno = duenoService.buscarDuenoPorCedula(cedula);
        if (dueno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Dueno>(dueno, HttpStatus.OK);
    }

    @GetMapping("/details")
    public ResponseEntity<Dueno> buscarDuenoDetail() {
        Dueno dueno = duenoService.buscarDuenoPorCedula(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (dueno == null) {
            return new ResponseEntity<Dueno>(dueno, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Dueno>(dueno, HttpStatus.OK);
    }

}
