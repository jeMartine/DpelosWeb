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

    public Dueno buscarDuenoPorId(Integer id) {
        return duenoRepository.getDuenoById(id);
    }

    @Override
    public Collection<Dueno> obtenerDuenos() {
        return duenoRepository.getDuenos();
    }

    @Override
    public void addDueno(Dueno dueno) {
        duenoRepository.addDueno(dueno);
    }

    @Override
    public void deleteDueno(Integer id) {
        duenoRepository.deleteDueno(id);
    }

    @Override
    public void updateDueno(Dueno dueno) {
        duenoRepository.updateDueno(dueno);
    }

}
