package com.web.dpelos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dpelos.dto.DrogaTratamientoCountDTO;
import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Tratamiento;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.DrogaRepository;
import com.web.dpelos.repository.TratamientoRepository;

import io.micrometer.observation.annotation.Observed;

@EnableAutoConfiguration
@Service
public class TratamientoServiceImplementation implements TratamientoService {
    @Autowired
    TratamientoRepository tratamientoRepository;
    @Autowired
    DrogaRepository drogaRepository;

    @Override
    public List<Tratamiento> obtenerTratamientos() {
        return tratamientoRepository.findAll();
    }

    @Override
    public Tratamiento buscarTratamientoPorId(Long id) {
        return tratamientoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tratamiento no encontrado con el id: " + id));
    }

    @Override
    public void addTratamiento(Tratamiento tratamiento) {
        tratamientoRepository.save(tratamiento);
    }

    @Override
    public void deleteTratamiento(Long id) {
        tratamientoRepository.deleteById(id);
    }

    @Override
    public void updateTratamiento(Tratamiento tratamiento) {
        tratamientoRepository.save(tratamiento);
    }

    @Override
    public List<Tratamiento> getActiveTratamientos() {
        return tratamientoRepository.findActiveTratamientos();
    }

    @Override
    public List<Tratamiento> buscarTratamientosActivosPorNombreMascota(String nombreMascota) {
        return tratamientoRepository.findTratamientosActivosByNombreMascota(nombreMascota);
    }

    @Override
    public List<Droga> obtenerMedicamentosActivosPorTratamiento(Long idTratamiento) {
        Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
        return tratamiento.getDrogas().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Droga> getMedicamentosPorTratamiento(Long idTratamiento) {
        return tratamientoRepository.findMedicamentosByIdTratamiento(idTratamiento);
    }

    @Override
    public void updateMedicamentosDelTratamiento(Long idTratamiento, List<Droga> medicamentos) {
        Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
        tratamiento.setDroga(medicamentos);
        tratamientoRepository.save(tratamiento);
    }

    // public List<TratamientoDrogasCountDTO> tratamientosMasUnidadesVendidas() {
    // return
    // tratamientoRepository.findTop3TratamientosOrderByDrogasCountDesc(PageRequest.of(0,
    // 3));
    // }

    @Override
    public List<String> tratamientosMasUnidadesVendidas() {
        Pageable top3 = PageRequest.of(0, 3);
        List<Tratamiento> topTratamientos = tratamientoRepository.findTop3TratamientosOrderByDrogasCountDesc(top3);

        return topTratamientos.stream()
                .map(t -> "idTratamiento: " + t.getIdTratamiento() + ", Drogas: " + t.getDrogas().size())
                .collect(Collectors.toList());
    }

    @Override
    public List<DrogaTratamientoCountDTO> tratamientosPorTipoDrogas() {
        return tratamientoRepository.findDrogaTratamientoCounts();
    }
}
