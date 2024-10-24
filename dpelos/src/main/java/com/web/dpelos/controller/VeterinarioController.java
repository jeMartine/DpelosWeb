package com.web.dpelos.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.VeterinarioService;

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
    public ResponseEntity< List<Veterinario>> findAll() {
        List <Veterinario> lista = veterinarioService.findAll();
        
        //Si la lista es vacia envio el codigo no encontrado con la lisata
        if(lista.isEmpty()){
            return new ResponseEntity<List<Veterinario>>(lista, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Veterinario>>(lista, HttpStatus.OK);
    }

    // Retornar un veterinario por su id
    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> findVetById(@PathVariable Long id) {
        Veterinario vet = veterinarioService.buscarVetPorId(id);
        if(vet ==null){
            return new ResponseEntity<Veterinario>(vet, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Veterinario>(vet, HttpStatus.OK);
    }

    // Crear un veterinario
    @PostMapping
    public ResponseEntity<Veterinario> crearVet(@RequestBody Veterinario vet) {
        Veterinario nuevoVet = veterinarioService.addVet(vet);
        if(nuevoVet == null){
            return new ResponseEntity<Veterinario>(nuevoVet, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Veterinario>(nuevoVet, HttpStatus.CREATED);

    }

    // Eliminar un veterinario por su id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVet(@PathVariable Long id) {
        veterinarioService.deleteVet(id);
        return new ResponseEntity<>("Veterinario Eliminado", HttpStatus.NOT_FOUND);
    }

    // Actualizar un veterinario
    @PutMapping("/update")
    public ResponseEntity<Veterinario> updateVet(@RequestBody Veterinario vet) {
        Veterinario vetFind = veterinarioService.updateVet(vet);
        return new ResponseEntity<>(vetFind, HttpStatus.OK);
    }

    /* Metodo para buscar veterianrio por su nombre */
    @GetMapping("/buscar")
    public ResponseEntity< Page<Veterinario>> buscarVeterinario(
            @RequestParam String nombre,
            @RequestParam int page,
            @RequestParam int size) {
        
        Page <Veterinario> vets = veterinarioService.buscarVeterinarioPorNombre(nombre, page, size);
        if(vets.isEmpty()){
            return new ResponseEntity<Page<Veterinario>>(vets, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Veterinario>>(vets, HttpStatus.OK);
    }

    /* Metodo para obtener todos los veterinarios por paginación */
    @GetMapping("/paginacion")
    public ResponseEntity<Page<Veterinario>> getVeterinarioPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {

        Page<Veterinario> veterinarioPaginadas = veterinarioService.getVeterinarioPaginadas(page, size);
        if(veterinarioPaginadas.isEmpty()){
            return new ResponseEntity<Page<Veterinario>>(veterinarioPaginadas, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Veterinario>>(veterinarioPaginadas, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> obtenerTotalVeterinarios() {
        long totalVeterinarios = veterinarioService.obtenerTotalVeterinarios();
        if(totalVeterinarios<=0){
            return new ResponseEntity<Long>(totalVeterinarios, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Long>(totalVeterinarios, HttpStatus.OK);
    }

    @GetMapping("/totalActivos")
    public ResponseEntity<Long> obtenerTotalVeterinariosActivos() {
        long totalVeterinariosActivos = veterinarioService.obtenerTotalVeterinariosActivos();
        if(totalVeterinariosActivos<=0){
            return new ResponseEntity<Long>(totalVeterinariosActivos, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Long>(totalVeterinariosActivos, HttpStatus.OK);
    }

    @GetMapping("/totalInactivos")
    public ResponseEntity<Long> obtenerTotalVeterinariosInactivos() {
        long totalVeterinariosInactivos = veterinarioService.obtenerTotalVeterinariosInactivos();
        if(totalVeterinariosInactivos<=0){
            return new ResponseEntity<Long>(totalVeterinariosInactivos, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Long>(totalVeterinariosInactivos, HttpStatus.OK);
    }
}
