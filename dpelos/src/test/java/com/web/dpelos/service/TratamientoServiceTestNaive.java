package com.web.dpelos.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

import com.web.dpelos.dto.DrogaTratamientoCountDTO;
import com.web.dpelos.entity.Droga;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Tratamiento;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.repository.DrogaRepository;
import com.web.dpelos.repository.MascotaRepository;
import com.web.dpelos.repository.TratamientoRepository;
import com.web.dpelos.repository.VeterinarioRepository;
import java.util.*;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TratamientoServiceTestNaive {
    @Autowired
    private TratamientoService tratamientoService;
    @Autowired
    private TratamientoRepository tratamientoRepository;
    @Autowired
    private DrogaRepository drogaRepository;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @BeforeEach
    public void setUp() {
        // Drogas for the tests
        Droga droga1 = new Droga("Droga A", 10.0f, 15.0f, 100, 50, "url1");
        Droga droga2 = new Droga("Droga B", 20.0f, 25.0f, 200, 100, "url2");
        Droga droga3 = new Droga("Droga C", 30.0f, 35.0f, 300, 150, "url3");
        Droga droga4 = new Droga("Droga D", 40.0f, 45.0f, 400, 200, "url4");
        drogaRepository.save(droga1);
        drogaRepository.save(droga2);
        drogaRepository.save(droga3);
        drogaRepository.save(droga4);

        // Mascotas for the tests
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        Mascota masc1 = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                sqlDate, true);
        Mascota masc2 = new Mascota("Rex", 4,
                "https://i.pinimg.com/1200x/07/84/ab/0784ab30e8c3c18e909620e531572b53.jpg", sqlDate, true);
        mascotaRepository.save(masc1);
        mascotaRepository.save(masc2);

        // Veterinarios for the tests
        Veterinario vet1 = new Veterinario("John", "Doe", "123456789", "password1", "url1", 10, true);
        Veterinario vet2 = new Veterinario("Jane", "Smith", "987654321", "password2", "url2", 20, true);
        veterinarioRepository.save(vet1);
        veterinarioRepository.save(vet2);

        // Tratamientos for the tests
        Tratamiento trat1 = new Tratamiento(sqlDate, "Tratamiento 1", true, "Recomendaciones 1");
        Tratamiento trat2 = new Tratamiento(sqlDate, "Tratamiento 2", false, "Recomendaciones 2");
        Tratamiento trat3 = new Tratamiento(sqlDate, "Tratamiento 3", true, "Recomendaciones 3");
        Tratamiento trat4 = new Tratamiento(sqlDate, "Tratamiento 4", false, "Recomendaciones 4");

        // Setting relationships
        trat1.setDroga(Arrays.asList(droga1, droga2));
        trat1.setMascota(masc1);
        trat1.setVeterinario(vet1);

        trat2.setDroga(Arrays.asList(droga3));
        trat2.setMascota(masc2);
        trat2.setVeterinario(vet2);

        trat3.setDroga(Arrays.asList(droga1, droga4));
        trat3.setMascota(masc1);
        trat3.setVeterinario(vet1);

        trat4.setDroga(Arrays.asList(droga2, droga3));
        trat4.setMascota(masc2);
        trat4.setVeterinario(vet2);

        // Saving tratamientos
        tratamientoRepository.save(trat1);
        tratamientoRepository.save(trat2);
        tratamientoRepository.save(trat3);
        tratamientoRepository.save(trat4);
    }

    @Test
    public void TratamientoService_obtenerTratamientos_ReturnsAllTratamientos() {
        // 1. Arrange
        // 2. Act
        List<Tratamiento> tratamientos = tratamientoService.obtenerTratamientos();
        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(4);
    }

    @Test
    public void TratamientoService_buscarTratamientoPorId_ReturnsTratamiento() {
        // 1. Arrange
        Tratamiento tratamiento = tratamientoRepository.findById(1L).get();
        // 2. Act
        Tratamiento foundTratamiento = tratamientoService.buscarTratamientoPorId(1L);
        // 3. Assert
        Assertions.assertThat(foundTratamiento).isNotNull();
        Assertions.assertThat(foundTratamiento.getIdTratamiento()).isEqualTo(tratamiento.getIdTratamiento());
    }

    @Test
    public void TratamientoService_addTratamiento_SavesTratamiento() {
        // 1. Arrange
        Mascota mascota = mascotaRepository.findById(1L).get();
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "New Tratamiento", true,
                "Recomendaciones");
        tratamiento.setMascota(mascota); // Set the mascota property

        // 2. Act
        tratamientoService.addTratamiento(tratamiento);

        // 3. Assert
        Assertions.assertThat(tratamientoRepository.findById(tratamiento.getIdTratamiento())).isPresent();
    }

    @Test
    public void TratamientoService_deleteTratamiento_DeletesTratamiento() {
        // 1. Arrange
        Tratamiento tratamiento = tratamientoRepository.findById(1L).get();
        // 2. Act
        tratamientoService.deleteTratamiento(1L);
        // 3. Assert
        Assertions.assertThat(tratamientoRepository.findById(1L)).isEmpty();
    }

    @Test
    public void TratamientoService_updateTratamiento_UpdatesTratamiento() {
        // 1. Arrange
        Tratamiento tratamiento = tratamientoRepository.findById(1L).get();
        tratamiento.setDescripcionTratamiento("Updated Description");
        // 2. Act
        tratamientoService.updateTratamiento(tratamiento);
        // 3. Assert
        Tratamiento updatedTratamiento = tratamientoRepository.findById(1L).get();
        Assertions.assertThat(updatedTratamiento.getDescripcionTratamiento()).isEqualTo("Updated Description");
    }

    @Test
    public void TratamientoService_getActiveTratamientos_ReturnsActiveTratamientos() {
        // 1. Arrange
        // 2. Act
        List<Tratamiento> activeTratamientos = tratamientoService.getActiveTratamientos();
        // 3. Assert
        Assertions.assertThat(activeTratamientos).isNotNull();
        Assertions.assertThat(activeTratamientos.size()).isEqualTo(2);
    }

    @Test
    public void TratamientoService_buscarTratamientosActivosPorNombreMascota_ReturnsTratamientos() {
        // 1. Arrange
        // 2. Act
        List<Tratamiento> tratamientos = tratamientoService.buscarTratamientosActivosPorNombreMascota("Toby");
        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void TratamientoService_obtenerMedicamentosActivosPorTratamiento_ReturnsMedicamentos() {
        // 1. Arrange
        // 2. Act
        List<Droga> drogas = tratamientoService.obtenerMedicamentosActivosPorTratamiento(1L);
        // 3. Assert
        Assertions.assertThat(drogas).isNotNull();
        Assertions.assertThat(drogas.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void TratamientoService_getMedicamentosPorTratamiento_ReturnsMedicamentos() {
        // 1. Arrange
        // 2. Act
        List<Droga> drogas = tratamientoService.getMedicamentosPorTratamiento(1L);
        // 3. Assert
        Assertions.assertThat(drogas).isNotNull();
        Assertions.assertThat(drogas.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void TratamientoService_updateMedicamentosDelTratamiento_UpdatesMedicamentos() {
        // 1. Arrange
        List<Droga> newDrogas = new ArrayList<>(Arrays.asList(drogaRepository.findById(3L).get()));

        // 2. Act
        tratamientoService.updateMedicamentosDelTratamiento(1L, newDrogas);

        // 3. Assert
        List<Droga> updatedDrogas = tratamientoService.getMedicamentosPorTratamiento(1L);
        Assertions.assertThat(updatedDrogas.size()).isEqualTo(1);
        Assertions.assertThat(updatedDrogas.get(0).getIdDroga()).isEqualTo(3L);
    }

    @Test
    @Transactional
    public void TratamientoService_tratamientosMasUnidadesVendidas_ReturnsTratamientos() {
        // 1. Arrange
        // 2. Act
        List<String> tratamientos = tratamientoService.tratamientosMasUnidadesVendidas();
        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
    }

    @Test
    public void TratamientoService_tratamientosPorTipoDrogas_ReturnsTratamientos() {
        // 1. Arrange
        // 2. Act
        List<DrogaTratamientoCountDTO> tratamientos = tratamientoService.tratamientosPorTipoDrogas();
        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
    }

    @Test
    public void TratamientoService_findTratamientosByMascotaId_ReturnsTratamientos() {
        // 1. Arrange
        // 2. Act
        List<Tratamiento> tratamientos = tratamientoService.findTratamientosByMascotaId(1L);
        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    @Test
    public void TratamientoService_addTratamientoToMascota_AddsTratamiento() {
        // 1. Arrange
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "New Tratamiento", true,
                "Recomendaciones");
        // 2. Act
        boolean result = tratamientoService.addTratamientoToMascota(1L, tratamiento);
        // 3. Assert
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(tratamientoRepository.findById(tratamiento.getIdTratamiento())).isPresent();
    }

    @Test
    public void TratamientoService_getTratamientosByVeterinario_ReturnsTratamientos() {
        // 1. Arrange
        // 2. Act
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosByVeterinario(1L);
        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }
}
