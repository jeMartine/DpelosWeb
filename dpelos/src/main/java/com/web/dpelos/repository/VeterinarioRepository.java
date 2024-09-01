package com.web.dpelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.Veterinario;


public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
    Veterinario findByCedulaVeterinario(String cedulaVeterinario);
}
