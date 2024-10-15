package com.web.dpelos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.service.DrogaService;


@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/droga")
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class DrogaController {
    @Autowired
    private DrogaService drogaService;

    /* Metodo para agregar drogas a la base de datos. */

    
    @GetMapping()
    public List<Droga> obtenerDrogas() {
        return drogaService.obtenerDrogas();
    }

    @GetMapping("/{id}")
    public Droga getDrogaById(@PathVariable Long id){
        return drogaService.buscarDrogaPorId(id);
    }

    @PostMapping()
    public void addDroga(@RequestBody Droga droga) {
        drogaService.addDroga(droga);
    }

    @PostMapping("/excel")
    public ResponseEntity<Map<String, String>> addDrogasExcel(@RequestBody List<Droga> drogas) {
    Map<String, String> response = new HashMap<>();
    if (!drogas.isEmpty()) {
        drogaService.saveAllExcel(drogas);
        response.put("message", "Medicamentos agregados exitosamente");
        return ResponseEntity.ok(response);
    } else {
        response.put("message", "El archivo enviado está vacío");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}

    @DeleteMapping("/{id}")
    public void deleteDroga(@PathVariable Long id){
        drogaService.deleteDroga(id);
    }

    @PutMapping()
    public void updateDroga(@RequestBody Droga droga){
        drogaService.updateDroga(droga);
    }

    /* Metodo para buscar medicamentos por su nombre */
    @GetMapping("/buscar")
    public Page<Droga> buscarMedicamentos(
            @RequestParam String nombre,
            @RequestParam int page,
            @RequestParam int size) {
        return drogaService.buscarMedicamentosPorNombre(nombre, page, size);
    }

    /* Metodo para obtener todas los medicamentos por paginación */
    @GetMapping("/paginacion")
    public ResponseEntity<Page<Droga>> getMedicamentosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {

        Page<Droga> medicamentosPaginados = drogaService.getMedicamentosPaginadas(page, size);
        return new ResponseEntity<>(medicamentosPaginados, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> obtenerTotalDrogas() {
        long totalDrogas = drogaService.obtenerTotalDrogas();
        return new ResponseEntity<>(totalDrogas, HttpStatus.OK);
    }
}
