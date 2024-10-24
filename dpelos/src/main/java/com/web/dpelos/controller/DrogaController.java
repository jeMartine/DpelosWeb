package com.web.dpelos.controller;

import java.util.List;

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
import com.web.dpelos.service.DrogaService;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/droga")
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class DrogaController {
    @Autowired
    private DrogaService drogaService;

    /* Metodo para agregar drogas a la base de datos. */

    /* Método para obtener todas las drogas */
    @GetMapping()
    public ResponseEntity<List<Droga>> obtenerDrogas() {
        List<Droga> drogas = drogaService.obtenerDrogas();
        if (drogas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Droga>>(drogas, HttpStatus.OK);
    }

   /* Método para obtener una droga por su ID */
   @GetMapping("/{id}")
   public ResponseEntity<Droga> getDrogaById(@PathVariable Long id) {
       Droga droga = drogaService.buscarDrogaPorId(id);
       if (droga == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<Droga>(droga, HttpStatus.OK);
   }

   /* Método para agregar una nueva droga */
   @PostMapping()
   public ResponseEntity<Droga> addDroga(@RequestBody Droga droga) {
       Droga nueva = drogaService.addDroga(droga);
       if(nueva == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  
       }
       return new ResponseEntity<Droga>(nueva, HttpStatus.CREATED);
   }

    @PostMapping("/excel")
    public ResponseEntity<String> addDrogasExcel(@RequestBody List<Droga> drogas) {
        boolean estado =drogaService.saveAllExcel(drogas);
        if(!estado){
            return new ResponseEntity<String>("Error al cargar medicamentos.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Medicamentos cargados éxitosamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDroga(@PathVariable Long id) {
        drogaService.deleteDroga(id);
        return new ResponseEntity<>("Medicamento eliminado", HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Droga> updateDroga(@RequestBody Droga droga) {
        Droga updateDroga = drogaService.updateDroga(droga);

        if(updateDroga == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Droga>(updateDroga, HttpStatus.OK);
    }

    /* Metodo para buscar medicamentos por su nombre */
    @GetMapping("/buscar")
    public ResponseEntity<Page<Droga>> buscarMedicamentos(
            @RequestParam String nombre,
            @RequestParam int page,
            @RequestParam int size) {
        Page<Droga> pdDroga = drogaService.buscarMedicamentosPorNombre(nombre, page, size);
        if(pdDroga.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Droga>>(pdDroga, HttpStatus.OK);
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

    @GetMapping("/totalVentas")
    public ResponseEntity<Double> obtenerTotalVentas() {
        double totalVentas = drogaService.obtenerTotalVentas();
        return new ResponseEntity<>(totalVentas, HttpStatus.OK);
    }

    @GetMapping("/totalGanancias")
    public ResponseEntity<Double> obtenerTotalGanancias() {
        double totalGanancias = drogaService.obtenerTotalGanancias();
        return new ResponseEntity<>(totalGanancias, HttpStatus.OK);
    }
}
