package com.web.dpelos.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.dpelos.dto.VeterinarioDTO;
import com.web.dpelos.entity.Veterinario;

public interface VeterinarioService {
    public VeterinarioDTO toDTO(Veterinario veterinario);
    public Veterinario buscarVetPorId(Long id);

    public List<Veterinario> findAll();

    public Veterinario addVet(Veterinario dueno);

    public void deleteVet(Long id);

    public Veterinario updateVet(Veterinario dueno);

    public Veterinario buscarVetPorCedula(String cedulaVet);

    //public Veterinario buscarVetLogin(String cedulaVet, String passwordVet);

    public long obtenerTotalVeterinarios();

    public Page<Veterinario> buscarVeterinarioPorNombre(String nombreVeterinario, int page, int size);

    public Page<Veterinario> getVeterinarioPaginadas(int page, int size);

    public long obtenerTotalVeterinariosActivos();

    public long obtenerTotalVeterinariosInactivos();
}
