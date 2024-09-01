package com.web.dpelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.Tratamiento;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    
}
