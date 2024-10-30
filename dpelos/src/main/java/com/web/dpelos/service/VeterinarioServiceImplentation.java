package com.web.dpelos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.VeterinarioRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class VeterinarioServiceImplentation implements VeterinarioService {

    @Autowired
    VeterinarioRepository veterinarioRepository;

    public Veterinario buscarVetPorId(Long id) {
        return veterinarioRepository.findById(id).get();
    }

    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Veterinario addVet(Veterinario Vet) {
        return veterinarioRepository.save(Vet);
    }

    @Transactional
    @Override
    public void deleteVet(Long id) {
        Veterinario Vet = veterinarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veterinario no encontrado con el id: " + id));
        ;

        if (Vet != null) {
            veterinarioRepository.deleteById(id);

        }

        // else {
        // throw new NotFoundException();
        // }

    }

    @Transactional
    @Override
    public Veterinario updateVet(Veterinario Vet) {
        return veterinarioRepository.save(Vet);
    }

    @Override
    public Veterinario buscarVetPorCedula(String cedulaVet) {
        return veterinarioRepository.findByCedulaVeterinario(cedulaVet);
    }

    @Override
    public Veterinario buscarVetLogin(String cedulaVet, String passwordVet) {
        return veterinarioRepository.findByCedulaVeterinarioAndPasswordVeterinario(cedulaVet, passwordVet);
    }

    public Page<Veterinario> buscarVeterinarioPorNombre(String nombreVeterinario, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return veterinarioRepository.findByNombreVeterinarioContainingIgnoreCase(nombreVeterinario, pageable);
    }

    public Page<Veterinario> getVeterinarioPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return veterinarioRepository.findAllVeterinario(pageable);
    }

    public long obtenerTotalVeterinarios() {
        return veterinarioRepository.count();
    }

    public long obtenerTotalVeterinariosActivos() {
        return veterinarioRepository.countByEstadoVeterinarioTrue();
    }

    public long obtenerTotalVeterinariosInactivos() {
        return veterinarioRepository.countByEstadoVeterinarioFalse();
    }
}
