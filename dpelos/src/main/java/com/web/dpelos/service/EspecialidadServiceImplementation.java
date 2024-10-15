package com.web.dpelos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.entity.Especialidad;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.EnfermedadMascotaRepository;
import com.web.dpelos.repository.EspecialidadRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class EspecialidadServiceImplementation implements EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public Especialidad buscarEspecialidadPorId(Long id) {
        return especialidadRepository.findById(id).get();
    }

    @Override
    public List<Especialidad> obtenerEspecialidades() {
        return especialidadRepository.findAll();
    }
}