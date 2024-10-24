package com.web.dpelos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Raza;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.RazaMascotaRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class RazaServiceImplementation implements RazaService {

    @Autowired
    private RazaMascotaRepository razaRepository;

    @Override
    public Raza buscarRazaPorId(Long id) {
        return razaRepository.findById(id).get();
    }

    @Override
    public List<Raza> obtenerRazas() {
        return razaRepository.findAll();
    }

    @Override
    public Raza addRaza(Raza raza) {
        return razaRepository.save(raza);
    }

    @Override
    public void deleteRaza(Long id) {
        Raza raza = razaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mascota not found with id: " + id));

        if (raza != null) {
            razaRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public Raza updateRaza(Raza raza) {
        return razaRepository.save(raza);
    }
}
