package com.web.dpelos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.web.dpelos.service.DuenoService;
import com.web.dpelos.service.VeterinarioService;


import jakarta.servlet.http.HttpSession;

/*Controlador de las peticiones relacionadas con la entidad Dueno */
@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/dueno")
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class DuenoControler {
    @Autowired
    DuenoService duenoService;

    @Autowired
    VeterinarioService veterinarioService;

    /*
     * Metodo que retorna un array dentor de un JSON que contiene los duenos
     * registrados.
     */
    @GetMapping()
    public List<Dueno> listaDuenos() {
        return duenoService.obtenerDuenos();
    }

    /*Retorna un dueño por su id*/
    @GetMapping("/{id}")
    public Dueno getDuenoById(@PathVariable Long id) {
        return duenoService.buscarDuenoPorId(id);
    }

    /* Metodo que agrega un dueno a la base de datos */
    @PostMapping()
    public void addDueno(@RequestBody Dueno dueno) {
        duenoService.addDueno(dueno);
    }

    /* Metodo que elimina el dueno segun el id */
    @DeleteMapping("/delete/{id}")
    public void deleteDueno(@PathVariable Long id) {
        duenoService.deleteDueno(id);
    }

    /* Metodo para mostrar el perfil de dueno actualizado */
    @PutMapping("/update")
    public void mostrarDuenoActualizado(@RequestBody Dueno dueno) {
        duenoService.updateDueno(dueno);
    }

    /* Metodo que busca al dueno segun su cedula */
    @GetMapping("/buscarCedula/{cedula}")
    public Dueno buscarDueno(@PathVariable("cedula") String cedula, HttpSession session) {
        return duenoService.buscarDuenoPorCedula(cedula);
    }

}
