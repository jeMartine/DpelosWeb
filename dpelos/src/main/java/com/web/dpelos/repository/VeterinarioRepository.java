package com.web.dpelos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Veterinario;


public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
    Veterinario findByCedulaVeterinario(String cedulaVeterinario);
    Veterinario findByCedulaVeterinarioAndPasswordVeterinario(String cedulaVet, String passwordVet);

    // Buscar un veterinario por nombre
    Page<Veterinario> findByNombreVeterinarioContainingIgnoreCase(String nombreVeterinario, Pageable pageable);

    // Obtener todas los veterinarios con paginación
    @Query("SELECT v FROM Veterinario v")
    Page<Veterinario> findAllVeterinario(Pageable pageable);
}
