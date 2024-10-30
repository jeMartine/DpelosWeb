package com.web.dpelos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Droga;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.DrogaRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class DrogaServiceImplementation implements DrogaService {

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
    public Droga addDroga(Droga raza) {
        return drogaRepository.save(raza);
    }

    @Override
    public boolean saveAllExcel(List<Droga> drogas) {
        try {
            List<Droga> drogasGuardadas = drogaRepository.saveAll(drogas);
            return drogasGuardadas.size() == drogas.size();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void venderDroga(Droga droga, int cantidad){

        int unidades = droga.getUnitVendidas();
        droga.setUnitVendidas(unidades+cantidad);

        drogaRepository.save(droga);
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
    public Droga updateDroga(Droga raza) {
        return drogaRepository.save(raza);
    }

    public Page<Droga> buscarMedicamentosPorNombre(String nombreMedicamento, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return drogaRepository.findByNombreDrogaContainingIgnoreCase(nombreMedicamento, pageable);
    }

    @Override
    public Page<Droga> getMedicamentosPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return drogaRepository.findAllDroga(pageable);
    }

    @Override
    public long obtenerTotalDrogas() {
        return drogaRepository.count();
    }

    @Override
    public double obtenerTotalVentas() {
        Double totalVentas = drogaRepository.findTotalVentas();
        return totalVentas != null ? totalVentas : 0.0;
    }

    @Override
    public double obtenerTotalGanancias() {
        Double totalVentas = drogaRepository.findTotalVentas();
        Double totalCost = drogaRepository.findTotalCost();

        if (totalVentas == null) {
            totalVentas = 0.0;
        }

        if (totalCost == null) {
            totalCost = 0.0;
        }

        return totalVentas - totalCost;
    }

    @Override
    public Long obtenerTotalUnidadesVendidas() {
        return drogaRepository.findTotalUnitsSold();
    }
}
