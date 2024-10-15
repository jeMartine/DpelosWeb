package com.web.dpelos.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Veterinario;


public interface VeterinarioService {
    public Veterinario buscarVetPorId(Long id);

    public List<Veterinario> findAll();

    public void addVet(Veterinario dueno);

    public void deleteVet(Long id);

    public void updateVet(Veterinario dueno);

    public Veterinario buscarVetPorCedula(String cedulaVet);

    public Veterinario buscarVetLogin(String cedulaVet, String passwordVet);

    public long obtenerTotalVeterinarios();

    public Page<Veterinario> buscarVeterinarioPorNombre(String nombreVeterinario, int page, int size);

    public Page<Veterinario> getVeterinarioPaginadas(int page, int size);
}
