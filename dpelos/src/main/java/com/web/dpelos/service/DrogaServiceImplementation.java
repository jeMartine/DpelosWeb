package com.web.dpelos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.DrogaRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class DrogaServiceImplementation implements DrogaService{
    
    @Autowired
    private DrogaRepository drogaRepository; 

    @Override
    public Droga buscarDrogaPorId(Long id) {
        return drogaRepository.findById(id).get();
    }

    @Override
    public List<Droga> obtenerDrogas() {
        return drogaRepository.findAll();
    }

    @Override
    public void addDroga(Droga raza) {
        drogaRepository.save(raza);
    }

    @Override
    public void saveAllExcel(List<Droga> drogas){
        drogaRepository.saveAll(drogas);
    }

    @Override
    public void deleteDroga(Long id) {
        Droga raza = drogaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mascota not found with id: " + id));

        if (raza != null) {
            drogaRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void updateDroga(Droga raza) {
        drogaRepository.save(raza);
    }

     public Page<Droga> buscarMedicamentosPorNombre(String nombreMedicamento, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return drogaRepository.findByNombreDrogaContainingIgnoreCase(nombreMedicamento, pageable);
    }

    public Page<Droga> getMedicamentosPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return drogaRepository.findAllDroga(pageable);
    }

    public long obtenerTotalDrogas() {
        return drogaRepository.count();
    }
}
