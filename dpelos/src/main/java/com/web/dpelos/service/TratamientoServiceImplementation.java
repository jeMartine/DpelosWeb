package com.web.dpelos.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.dpelos.dto.DrogaTratamientoCountDTO;
import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Tratamiento;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.DrogaRepository;
import com.web.dpelos.repository.MascotaRepository;
import com.web.dpelos.repository.TratamientoRepository;

@EnableAutoConfiguration
@Service
public class TratamientoServiceImplementation implements TratamientoService {
    @Autowired
    TratamientoRepository tratamientoRepository;
    @Autowired
    DrogaRepository drogaRepository;

    @Autowired
    MascotaRepository mascotaRepository;

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
    public Tratamiento addTratamiento(Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
    }

    @Override
    public void deleteTratamiento(Long id) {
        tratamientoRepository.deleteById(id);
    }

    @Override
    public Tratamiento updateTratamiento(Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
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
        Optional<Tratamiento> tratamientoOpt = tratamientoRepository.findById(idTratamiento);
        if (tratamientoOpt.isPresent()) {
            Tratamiento tratamiento = tratamientoOpt.get();
            return tratamiento.getDroga(); // Ensure this returns the list of Droga
        }
        return Collections.emptyList();
    }

    @Override
    public Tratamiento updateMedicamentosDelTratamiento(Long idTratamiento, List<Droga> medicamentos) {
        Tratamiento tratamiento = tratamientoRepository.findById(idTratamiento)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
        tratamiento.setDroga(medicamentos);
        return tratamientoRepository.save(tratamiento);
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

    @Override
    public List<Tratamiento> findTratamientosByMascotaId(Long idMascota) {
        return tratamientoRepository.findByMascotaId(idMascota);
    }

    public boolean addTratamientoToMascota(Long idMascota, Tratamiento tratamiento) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(idMascota);

        if (mascotaOptional.isPresent()) {
            Mascota mascota = mascotaOptional.get();
            tratamiento.setMascota(mascota); // Asociar el tratamiento con la mascota
            tratamientoRepository.save(tratamiento);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Tratamiento> getTratamientosByVeterinario(Long idVeterinario) {
        return tratamientoRepository.findTratamientosByVeterinario(idVeterinario);
    }
}
