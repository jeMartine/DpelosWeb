package com.web.dpelos.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.DuenoRepository;
import com.web.dpelos.repository.MascotaRepository;

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
    public Collection<Mascota> obtenerMascotas() {
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
    public void addMascota(Mascota mascota) {
        mascotaRepository.save(mascota);
    }

    @Override
    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public void updateMascota(Mascota mascota) {
        mascotaRepository.save(mascota);
    }
}
