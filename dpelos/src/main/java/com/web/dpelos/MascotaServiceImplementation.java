package com.web.dpelos;

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
    public Mascota buscarMascotaPorId(Integer id) {
        return mascotaRepository.getMascotaById(id);
    }

    @Override
    public Collection<Mascota> obtenerMascotas() {
        return mascotaRepository.getMascotas();
    }
}
