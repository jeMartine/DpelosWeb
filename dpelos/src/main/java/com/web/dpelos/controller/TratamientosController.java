package com.web.dpelos.controller;

import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dpelos.dto.DrogaTratamientoCountDTO;
import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Tratamiento;
// import com.web.dpelos.entity.TratamientoDrogasCountDTO;
import com.web.dpelos.service.TratamientoService;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/tratamiento") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class TratamientosController {

    @Autowired
    TratamientoService tratamientoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Tratamiento>> listaTratamientos() {
        List<Tratamiento> lista = tratamientoService.obtenerTratamientos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /*
     * Metodo que retorna la informacion de un trataamiento segun su respectivo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> mostrarTratamientoPorID(@PathVariable Long id) {
        Tratamiento tratamiento = tratamientoService.buscarTratamientoPorId(id);
        if (tratamiento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tratamiento, HttpStatus.OK);
    }

    /* Metodo para agregar mascotas a la base de datos */
    @PostMapping("/add")
    public ResponseEntity<Tratamiento> addTratamiento(@RequestBody Tratamiento tratamiento) {
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);

        tratamiento.setFechaAdministracion(sqlDate);
        Tratamiento trat = tratamientoService.addTratamiento(tratamiento);

        if(trat == null){
            return new ResponseEntity<Tratamiento>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(trat, HttpStatus.CREATED);
    }


    //Metodo que permite añadir un tratamiento a una mascota
    @PostMapping("/add/{idMascota}")
    public ResponseEntity<String> addTratamientoToMascota(
            @PathVariable("idMascota") Long idMascota,
            @RequestBody Tratamiento tratamiento) {
        
        boolean isCreated = tratamientoService.addTratamientoToMascota(idMascota, tratamiento);
        
        if (!isCreated) {
            // Si hubo algún error al crear el tratamiento
            return new ResponseEntity<String>("Hubo un error al agregar el tratameinto", HttpStatus.BAD_REQUEST);
        }
        // Si el tratamiento fue creado con éxito
        return new ResponseEntity<String>("Tratamiento creado exitosamente.", HttpStatus.OK);
    }

    /* Metodo modificado para borrar una mascota usando su id */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTratamiento(@PathVariable Long id) {
        tratamientoService.deleteTratamiento(id);
        return new ResponseEntity<String>("Tratamiento eliminado", HttpStatus.NOT_FOUND);
    }

    /*Metodo que permite actualizar un tratramiento */
    @PutMapping("/update")
    public ResponseEntity<Tratamiento> updateTratamiento(@RequestBody Tratamiento tratamiento) {
        
        Tratamiento trat = tratamientoService.updateTratamiento(tratamiento);
        if(trat == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }    
        return new ResponseEntity<Tratamiento>(trat,HttpStatus.OK);
        
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Tratamiento>> getActiveTratamientos() {
        List <Tratamiento> tratActivos = tratamientoService.getActiveTratamientos();

        if(tratActivos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Tratamiento>>(tratActivos, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Tratamiento>> buscarTratamientosActivosPorNombreMascota(@RequestParam String nombre) {
        List<Tratamiento> tratamientos = tratamientoService.buscarTratamientosActivosPorNombreMascota(nombre);
        if (tratamientos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tratamientos, HttpStatus.OK);
    }

    @GetMapping("/{idTratamiento}/medicamentos")
    public ResponseEntity<List<Droga>> getMedicamentosPorTratamiento(@PathVariable Long idTratamiento) {
        List<Droga> medicamentos = tratamientoService.getMedicamentosPorTratamiento(idTratamiento);
        if(medicamentos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Droga>>(medicamentos, HttpStatus.OK);
    }

    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<Tratamiento>> findTratamientosByMascotaId(@PathVariable Long idMascota) {
        List<Tratamiento> tratamientos = tratamientoService.findTratamientosByMascotaId(idMascota);
        if (tratamientos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tratamientos, HttpStatus.OK);
    }

    // Actualizar medicamentos del tratamiento
    @PutMapping("/{idTratamiento}/medicamentos")
    public ResponseEntity<Void> updateMedicamentosDelTratamiento(
            @PathVariable Long idTratamiento,
            @RequestBody List<Droga> medicamentos) {
        Tratamiento  trat = tratamientoService.updateMedicamentosDelTratamiento(idTratamiento, medicamentos);
        if(trat==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/top3")
    public ResponseEntity<List<String>> getTop3TratamientosMasUnidadesVendidas() {
        List<String> top = tratamientoService.tratamientosMasUnidadesVendidas();
        if(top.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<String>>(top, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tratamientos-por-tipo-drogas")
    public ResponseEntity< List<DrogaTratamientoCountDTO>> getTratamientosPorTipoDrogas() {
        List<DrogaTratamientoCountDTO> droga = tratamientoService.tratamientosPorTipoDrogas();

        if(droga.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<DrogaTratamientoCountDTO>>(droga, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<List<Tratamiento>> getTratamientosByVeterinario(@PathVariable Long idVeterinario) {
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosByVeterinario(idVeterinario);
        if (tratamientos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Tratamiento>>(tratamientos, HttpStatus.OK);
    }
}
