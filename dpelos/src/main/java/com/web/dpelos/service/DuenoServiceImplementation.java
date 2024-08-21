package com.web.dpelos.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.repository.DuenoRepository;

@EnableAutoConfiguration
@Service
public class DuenoServiceImplementation implements DuenoService {
    @Autowired
    DuenoRepository duenoRepository;

    public Dueno buscarDuenoPorId(Long id) {
        System.out.println("el id es: " + id);
        return duenoRepository.findById(id).get();
    }

    @Override
    public Collection<Dueno> obtenerDuenos() {
        return duenoRepository.findAll();
    }

    @Override
    public void addDueno(Dueno dueno) {
        duenoRepository.save(dueno);
    }

    @Override
    public void deleteDueno(Long id) {
        duenoRepository.deleteById(id);
    }

    @Override
    public void updateDueno(Dueno dueno) {
        duenoRepository.save(dueno);
    }

}
