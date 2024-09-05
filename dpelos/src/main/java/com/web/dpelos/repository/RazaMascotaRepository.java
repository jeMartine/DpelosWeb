package com.web.dpelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.Raza;

public interface RazaMascotaRepository extends JpaRepository<Raza, Long> {
    
}
