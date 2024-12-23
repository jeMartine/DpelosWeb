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
import com.web.dpelos.service.DrogaService;
// import com.web.dpelos.entity.TratamientoDrogasCountDTO;
import com.web.dpelos.service.TratamientoService;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/tratamiento") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class TratamientosController {

    @Autowired
    TratamientoService tratamientoService;

    @Autowired
    DrogaService drogaService;

    @GetMapping("/lista")
    public List<Tratamiento> listaTratamientos() {
        return tratamientoService.obtenerTratamientos();
    }

    /*
     * Metodo que retorna la informacion de un trataamiento segun su respectivo ID
     */
    @GetMapping("/{id}")
    public Tratamiento mostrarTratamientoPorID(@PathVariable Long id) {
        return tratamientoService.buscarTratamientoPorId(id);
    }

    /* Metodo para agregar mascotas a la base de datos */
    @PostMapping("/add")
    public void addTratamiento(@RequestBody Tratamiento tratamiento) {
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);

        tratamiento.setFechaAdministracion(sqlDate);
        tratamientoService.addTratamiento(tratamiento);
    }

    @PostMapping("/add/{idMascota}")
    public ResponseEntity<String> addTratamientoToMascota(
            @PathVariable("idMascota") Long idMascota,
            @RequestBody Tratamiento tratamiento) {

        boolean isCreated = tratamientoService.addTratamientoToMascota(idMascota, tratamiento);

        if (isCreated) {
            // Si el tratamiento fue creado con éxito
            return ResponseEntity.ok("Tratamiento creado exitosamente.");
        } else {
            // Si hubo algún error al crear el tratamiento
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el tratamiento.");
        }
    }

    /* Metodo modificado para borrar una mascota usando su id */
    @DeleteMapping("/delete/{id}")
    public void deleteTratamiento(@PathVariable Long id) {
        tratamientoService.deleteTratamiento(id);
    }

    /* Metodo que permite actualizar un tratramiento */
    @PutMapping("/update")
    public ResponseEntity<String> updateTratamiento(@RequestBody Tratamiento tratamiento) {
        try {
            tratamientoService.updateTratamiento(tratamiento);
            if(tratamiento.isEstado()==false){
                for (Droga droga: tratamiento.getDrogas()){
                    drogaService.venderDroga(droga, 1);
                }
            }
            return ResponseEntity.ok("Tratamiento actualizado correctamente.");
        } catch (Exception e) {
            String errorMessage = "Error al actualizar el tratamiento";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/activos")
    public List<Tratamiento> getActiveTratamientos() {
        return tratamientoService.getActiveTratamientos();
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
        return new ResponseEntity<>(medicamentos, HttpStatus.OK);
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
        tratamientoService.updateMedicamentosDelTratamiento(idTratamiento, medicamentos);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/top3")
    public List<String> getTop3TratamientosMasUnidadesVendidas() {
        return tratamientoService.tratamientosMasUnidadesVendidas();
    }

    @GetMapping("/tratamientos-por-tipo-drogas")
    public List<DrogaTratamientoCountDTO> getTratamientosPorTipoDrogas() {
        return tratamientoService.tratamientosPorTipoDrogas();
    }

    @GetMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<List<Tratamiento>> getTratamientosByVeterinario(@PathVariable Long idVeterinario) {
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosByVeterinario(idVeterinario);
        if (tratamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tratamientos);
    }
}
