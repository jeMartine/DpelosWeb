package com.web.dpelos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    @Query("SELECT t FROM Tratamiento t WHERE t.estado = true")
    List<Tratamiento> findActiveTratamientos();

    @Query("SELECT t FROM Tratamiento t WHERE t.mascota.nombreMascota LIKE %:nombreMascota% AND t.estado = true")
    List<Tratamiento> findTratamientosActivosByNombreMascota(@Param("nombreMascota") String nombreMascota);

    List<Droga> findMedicamentosByIdTratamiento(Long idTratamiento);


}
