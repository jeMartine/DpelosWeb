package com.web.dpelos.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.DuenoRepository;
import com.web.dpelos.repository.MascotaRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class DuenoServiceImplementation implements DuenoService {
    @Autowired
    DuenoRepository duenoRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    public Dueno buscarDuenoPorId(Long id) {
        return duenoRepository.findById(id).get();
    }

    @Override
    public List<Dueno> obtenerDuenos() {
        return duenoRepository.findAll();
    }

    @Override
    public void addDueno(Dueno dueno) {
        duenoRepository.save(dueno);
    }

    @Transactional
    @Override
    public void deleteDueno(Long id) {
        Dueno dueno = duenoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dueno no encontrado con el id: " + id));

        if (dueno != null) {
            mascotaRepository.deleteAllByDueno(dueno);
            duenoRepository.deleteById(id);
        }
    }

    @Transactional
    @Override
    public void updateDueno(Dueno dueno) {
        duenoRepository.save(dueno);
    }

    @Override
    public Dueno buscarDuenoPorCedula(String cedulaDueno) {
        return duenoRepository.findByCedulaDueno(cedulaDueno);
    }
}
