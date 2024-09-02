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
    public Raza buscarRazaPorId (Long id){
        return razaRepository.findById(id).get();
    }

    @Override
    public List<Raza> obtenerRazas(){
        return razaRepository.findAll();
    }

    @Override
    public void addRaza(Raza raza){
        razaRepository.save(raza);
    }

    @Override
    public void deleteRaza(Long id){
        Raza raza = razaRepository.findById(id).get();

        if(raza!=null){
            razaRepository.deleteById(id);
        }else{
            throw new NotFoundException(id.toString());
        }
    }

    @Override
    @Transactional
    public void updateRaza(Raza raza){
        razaRepository.save(raza);
    }
}
