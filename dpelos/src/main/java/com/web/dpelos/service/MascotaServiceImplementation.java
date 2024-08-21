package com.web.dpelos.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Mascota;
import com.web.dpelos.repository.MascotaRepository;

@EnableAutoConfiguration
@Service
public class MascotaServiceImplementation implements MascotaService {
    @Autowired
    MascotaRepository mascotaRepository;

    @Override
    public Mascota buscarMascotaPorId(Long id) {
        return mascotaRepository.findById(id).get();
    }

    @Override
    public Collection<Mascota> obtenerMascotas() {
        return mascotaRepository.findAll();
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
