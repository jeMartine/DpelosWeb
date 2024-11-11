package com.web.dpelos.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dpelos.dto.VeterinarioDTO;
import com.web.dpelos.dto.VeterinarioMapper;
import com.web.dpelos.entity.UserEntity;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.repository.UserRepository;
import com.web.dpelos.security.CustomUserDetailService;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserDetailService customUserDetailService;

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
    public ResponseEntity crearVet(@RequestBody Veterinario vet) {
        if(userRepository.existsByUsername(vet.getCedulaVeterinario())){
            return new ResponseEntity<String>("Este veterinario ya existe", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = customUserDetailService.veterinarioToUser(vet);
        vet.setUser(userEntity);
        Veterinario vetDB = veterinarioService.addVet(vet);
        VeterinarioDTO nuevoVet = VeterinarioMapper.INSTACE.convert(vetDB);
        if(nuevoVet == null){
            return new ResponseEntity<VeterinarioDTO>(nuevoVet, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<VeterinarioDTO>(nuevoVet, HttpStatus.CREATED);
        /*Veterinario nuevoVet = veterinarioService.addVet(vet);
        VeterinarioDTO veterinarioDTO = VeterinarioMapper.INSTACE.convert(nuevoVet);
        if(nuevoVet == null){
            return new ResponseEntity<VeterinarioDTO>(veterinarioDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<VeterinarioDTO>(veterinarioDTO, HttpStatus.CREATED);*/

    }

    // Eliminar un veterinario por su id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVet(@PathVariable Long id) {
        veterinarioService.deleteVet(id);
        return new ResponseEntity<>("Veterinario Eliminado", HttpStatus.OK);
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

    @GetMapping("/details")
    public ResponseEntity<VeterinarioDTO> buscarVeterinarioDetail() {
        Veterinario veterinario = veterinarioService.buscarVetPorCedula(
            SecurityContextHolder.getContext().getAuthentication().getName()
        );

        VeterinarioDTO veterinarioDTO = VeterinarioMapper.INSTACE.convert(veterinario);

        if(veterinario == null) {
            return new ResponseEntity<VeterinarioDTO>(veterinarioDTO, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<VeterinarioDTO>(veterinarioDTO, HttpStatus.OK); 
    }
    
}
