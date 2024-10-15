package com.web.dpelos.controller;

import java.lang.annotation.Repeatable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.VeterinarioService;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/vet") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class VeterinarioController {
    @Autowired
    VeterinarioService veterinarioService;

    // Retornar todos los veterinarios
    @GetMapping
    public List<Veterinario> findAll() {
        return veterinarioService.findAll();
    }

    // Retornar un veterinario por su id
    @GetMapping("/{id}")
    public Veterinario findVetById(@PathVariable Long id) {
        return veterinarioService.buscarVetPorId(id);
    }

    // Crear un veterinario
    @PostMapping
    public void crearVet(@RequestBody Veterinario vet) {
        System.out.println("Password: " + vet.getPasswordVeterinario()); // Verificar si llega la contraseña
        veterinarioService.addVet(vet);
    }

    // Eliminar un veterinario por su id
    @DeleteMapping("/delete/{id}")
    public void deleteVet(@PathVariable Long id) {
        veterinarioService.deleteVet(id);
    }

    // Actualizar un veterinario
    @PutMapping("/update")
    public void updateVet(@RequestBody Veterinario vet) {
        veterinarioService.updateVet(vet);
    }

    /* Metodo para buscar veterianrio por su nombre */
    @GetMapping("/buscar")
    public Page<Veterinario> buscarVeterinario(
            @RequestParam String nombre,
            @RequestParam int page,
            @RequestParam int size) {
        return veterinarioService.buscarVeterinarioPorNombre(nombre, page, size);
    }

    /* Metodo para obtener todos los veterinarios por paginación */
    @GetMapping("/paginacion")
    public ResponseEntity<Page<Veterinario>> getVeterinarioPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {

        Page<Veterinario> veterinarioPaginadas = veterinarioService.getVeterinarioPaginadas(page, size);
        return new ResponseEntity<>(veterinarioPaginadas, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> obtenerTotalVeterinarios() {
        long totalVeterinarios = veterinarioService.obtenerTotalVeterinarios();
        return new ResponseEntity<>(totalVeterinarios, HttpStatus.OK);
    }

    @GetMapping("/totalActivos")
    public ResponseEntity<Long> obtenerTotalVeterinariosActivos() {
        long totalVeterinariosActivos = veterinarioService.obtenerTotalVeterinariosActivos();
        return new ResponseEntity<>(totalVeterinariosActivos, HttpStatus.OK);
    }

    @GetMapping("/totalInactivos")
    public ResponseEntity<Long> obtenerTotalVeterinariosInactivos() {
        long totalVeterinariosInactivos = veterinarioService.obtenerTotalVeterinariosInactivos();
        return new ResponseEntity<>(totalVeterinariosInactivos, HttpStatus.OK);
    }
}
