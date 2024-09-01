package com.web.dpelos.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Veterinario;


public interface VeterinarioService {
    public Veterinario buscarVetPorId(Long id);

    public Collection<Veterinario> obtenerVets();

    public void addVet(Veterinario dueno);

    public void deleteVet(Long id);

    public void updateVet(Veterinario dueno);

    public Veterinario buscarVetPorCedula(String cedulaVet);

    public Veterinario buscarVetLogin(String cedulaVet, String passwordVet);
}