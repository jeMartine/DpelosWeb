package com.web.dpelos.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // Buscar una mascota por nombre
    Page<Mascota> findByNombreMascotaContainingIgnoreCase(String nombreMascota, Pageable pageable);

    // Eliminar las mascotas de un dueño
    void deleteAllByDueno(Dueno dueno);

    // Obtener todas las mascotas con paginación
    @Query("SELECT m FROM Mascota m")
    Page<Mascota> findAllMascotas(Pageable pageable);
}
