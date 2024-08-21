package com.web.dpelos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dpelos.entity.Mascota;

/* */
@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
   
}
