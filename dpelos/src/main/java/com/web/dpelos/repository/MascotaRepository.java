package com.web.dpelos.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Mascota;

/* */
@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    @Query("SELECT m FROM Mascota m WHERE m.dueno = :dueno")
    Collection<Mascota> findByIdDueno(@Param("dueno") Dueno dueno);


    //Eliminar las mascotas de un dueño
    void deleteAllByDueno(Dueno dueno);
}
