package com.web.dpelos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Tratamiento;
import com.web.dpelos.repository.TratamientoRepository;

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

    //public List<Droga> getMedicamentosPorTratamiento(Long idTratamiento);

    //public void updateMedicamentosDelTratamiento(Long idTratamiento, List<Droga> medicamentos);

}
