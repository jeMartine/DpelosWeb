package com.web.dpelos.service;

import org.springframework.stereotype.Service;

import com.web.dpelos.dto.DrogaTratamientoCountDTO;
import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Tratamiento;

import java.util.List;

@Service
public interface TratamientoService {

    public List<Tratamiento> obtenerTratamientos();

    public Tratamiento buscarTratamientoPorId(Long id);

    public void addTratamiento(Tratamiento tratamiento);

    public void deleteTratamiento(Long id);

    public void updateTratamiento(Tratamiento tratamiento);

    public List<Tratamiento> getActiveTratamientos();

    public List<Tratamiento> buscarTratamientosActivosPorNombreMascota(String nombreMascota);

    public List<Droga> obtenerMedicamentosActivosPorTratamiento(Long idTratamiento);

    public List<Droga> getMedicamentosPorTratamiento(Long idTratamiento);

    public void updateMedicamentosDelTratamiento(Long idTratamiento, List<Droga> medicamentos);

    public List<String> tratamientosMasUnidadesVendidas();

    List<DrogaTratamientoCountDTO> tratamientosPorTipoDrogas();

    List<Tratamiento> findTratamientosByMascotaId(Long idMascota);

    public boolean addTratamientoToMascota(Long idMascota, Tratamiento tratamiento);
}
