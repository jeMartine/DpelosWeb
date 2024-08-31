package com.web.dpelos.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.MascotaRepository;
import com.web.dpelos.repository.VeterinarioRepository;

import jakarta.transaction.Transactional;

public class VeterinarioServiceImplentation implements VeterinarioService{
  

    @Autowired
    VeterinarioRepository veterinarioRepository;

    public Veterinario buscarVetPorId(Long id) {
        return veterinarioRepository.findById(id).get();
    }

    @Override
    public Collection<Veterinario> obtenerVets() {
        return veterinarioRepository.findAll();
    }

    @Override
    public void addVet(Veterinario Vet) {
        veterinarioRepository.save(Vet);
    }

    @Transactional
    @Override
    public void deleteVet(Long id) {
        Veterinario Vet = veterinarioRepository.findById(id).get();

        if(Vet!=null){
            veterinarioRepository.deleteById(id);

        }else{
            throw new NotFoundException(id.toString());
        }

    }

    @Transactional
    @Override
    public void updateVet(Veterinario Vet) {
        veterinarioRepository.save(Vet);
    }

    @Override
    public Veterinario buscarVetPorCedula(String cedulaVet) {
        return veterinarioRepository.findByCedulaVeterinario(cedulaVet);
    }
}
