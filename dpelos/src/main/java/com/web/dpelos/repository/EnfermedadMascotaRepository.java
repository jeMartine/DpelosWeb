package com.web.dpelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.Enfermedad;

public interface EnfermedadMascotaRepository extends JpaRepository<Enfermedad, Long> {
    
}
