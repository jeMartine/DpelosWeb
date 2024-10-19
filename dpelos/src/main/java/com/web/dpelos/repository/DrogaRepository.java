package com.web.dpelos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.dpelos.dto.DrogaTratamientoCountDTO;
import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Mascota;

public interface DrogaRepository extends JpaRepository<Droga, Long> {
    // Buscar una mascota por nombre
    Page<Droga> findByNombreDrogaContainingIgnoreCase(String nombreDroga, Pageable pageable);

    // Obtener todas las mascotas con paginación
    @Query("SELECT m FROM Droga m")
    Page<Droga> findAllDroga(Pageable pageable);

    @Query("SELECT SUM(d.unitVendidas * d.precioVenta) FROM Droga d")
    Double findTotalVentas();

    @Query("SELECT SUM(d.unitVendidas * d.precioCompra) FROM Droga d")
    Double findTotalCost();

    // @Query("SELECT new com.web.dpelos.dto.DrogaTratamientoCountDTO(d.idDroga,
    // d.nombreDroga, COUNT(t)) " +
    // "FROM Droga d JOIN d.tratamientos t GROUP BY d.idDroga, d.nombreDroga")
    // List<DrogaTratamientoCountDTO> findDrogaTratamientoCounts();
}
