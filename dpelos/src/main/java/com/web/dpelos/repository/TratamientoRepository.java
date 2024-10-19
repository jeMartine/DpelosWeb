package com.web.dpelos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.dpelos.dto.DrogaTratamientoCountDTO;
import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Tratamiento;
// import com.web.dpelos.entity.TratamientoDrogasCountDTO;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    @Query("SELECT t FROM Tratamiento t WHERE t.estado = true")
    List<Tratamiento> findActiveTratamientos();

    @Query("SELECT t FROM Tratamiento t WHERE t.mascota.nombreMascota LIKE %:nombreMascota% AND t.estado = true")
    List<Tratamiento> findTratamientosActivosByNombreMascota(@Param("nombreMascota") String nombreMascota);

    // List<Droga> findMedicamentosByIdTratamiento(Long idTratamiento);

    @Query("SELECT t.drogas FROM Tratamiento t WHERE t.idTratamiento = :idTratamiento")
    List<Droga> findMedicamentosByIdTratamiento(@Param("idTratamiento") Long idTratamiento);

    // @Query("SELECT new com.web.dpelos.dto.TratamientoDrogasCountDTO(t.id,
    // COUNT(d)) " +
    // "FROM Tratamiento t LEFT JOIN t.drogas d GROUP BY t.id ORDER BY COUNT(d)
    // DESC")
    // List<TratamientoDrogasCountDTO>
    // findTop3TratamientosOrderByDrogasCountDesc(Pageable pageable);
    @Query("SELECT t FROM Tratamiento t LEFT JOIN t.drogas d GROUP BY t.idTratamiento ORDER BY COUNT(d) DESC")
    List<Tratamiento> findTop3TratamientosOrderByDrogasCountDesc(Pageable pageable);

    @Query("SELECT new com.web.dpelos.dto.DrogaTratamientoCountDTO(d.idDroga, d.nombreDroga, COUNT(t)) " +
            "FROM Tratamiento t JOIN t.drogas d GROUP BY d.idDroga, d.nombreDroga")
    List<DrogaTratamientoCountDTO> findDrogaTratamientoCounts();
}
