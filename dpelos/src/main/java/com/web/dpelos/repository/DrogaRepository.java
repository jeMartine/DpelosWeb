package com.web.dpelos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.Droga;

public interface DrogaRepository extends JpaRepository<Droga, Long> {
    List<Droga> findByNombreDrogaContainingIgnoreCase(String nombreDroga);
}
