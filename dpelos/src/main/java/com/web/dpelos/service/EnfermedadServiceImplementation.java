package com.web.dpelos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.EnfermedadMascotaRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class EnfermedadServiceImplementation implements EnfermedadService {

    @Autowired
    private EnfermedadMascotaRepository enfermedadRepository;

    @Override
    public Enfermedad buscarEnfermedadPorId(Long id) {
        return enfermedadRepository.findById(id).get();
    }

    @Override
    public List<Enfermedad> obtenerEnfermedades() {
        return enfermedadRepository.findAll();
    }

    @Override
    public Enfermedad addEnfermedad(Enfermedad enfermedad) {
        return enfermedadRepository.save(enfermedad);
    }

    @Override
    public void deleteEnfermedad(Long id) {
        Enfermedad enfermedad = enfermedadRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mascota not found with id: " + id));

        if (enfermedad != null) {
            enfermedadRepository.delete(enfermedad);
        }

        // else {
        // throw new NotFoundException();
        // }
    }

    @Override
    @Transactional
    public Enfermedad updateEnfermedad(Enfermedad raza) {
        return enfermedadRepository.save(raza);
    }
}
