package com.web.dpelos.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Tratamiento;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.DuenoRepository;
import com.web.dpelos.repository.MascotaRepository;
import com.web.dpelos.repository.TratamientoRepository;

@EnableAutoConfiguration
@Service
public class MascotaServiceImplementation implements MascotaService {
    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    private DuenoRepository duenoRepository;

    @Override
    public Mascota buscarMascotaPorId(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mascota no encontrada con el id: " + id));
    }

    @Override
    public List<Mascota> obtenerMascotas() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> obtenerMascotasDelDueno(Long idDueno) {
        Dueno dueno = duenoRepository.findById(idDueno).orElse(null);
        if (dueno != null) {
            return new ArrayList<>(mascotaRepository.findByIdDueno(dueno));
        }
        return Collections.emptyList();

    }

    @Override
    public Mascota addMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public Mascota updateMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Page<Mascota> buscarMascotasPorNombre(String nombreMascota, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mascotaRepository.findByNombreMascotaContainingIgnoreCase(nombreMascota, pageable);
    }

    public Page<Mascota> getMascotasPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mascotaRepository.findAllMascotas(pageable);
    }

    public long obtenerTotalMascotas() {
        return mascotaRepository.count();
    }

    public long obtenerTotalMascotasActivas() {
        return mascotaRepository.countByEstadoTrue();
    }
}
