package com.web.dpelos.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

// @SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TratamientoServiceTestMock {
    @InjectMocks
    private TratamientoServiceImplementation tratamientoService;
    @Mock
    private TratamientoRepository tratamientoRepository;
    @Mock
    private DrogaRepository drogaRepository;
    @Mock
    private MascotaRepository mascotaRepository;
    @Mock
    private VeterinarioRepository veterinarioRepository;

    // @BeforeEach
    // public void setUp() {
    // // Setup mock data
    // Mascota mascota = new Mascota("Toby", 3,
    // "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
    // Date.valueOf(LocalDate.now()), true);
    // Tratamiento tratamiento1 = new Tratamiento(Date.valueOf(LocalDate.now()),
    // "Tratamiento 1", true,
    // "Recomendaciones 1");
    // tratamiento1.setIdTratamiento(1L);
    // tratamiento1.setMascota(mascota);

    // Tratamiento tratamiento2 = new Tratamiento(Date.valueOf(LocalDate.now()),
    // "Tratamiento 2", false,
    // "Recomendaciones 2");
    // tratamiento2.setIdTratamiento(2L);
    // tratamiento2.setMascota(mascota);

    // Mockito.when(tratamientoRepository.findAll()).thenReturn(Arrays.asList(tratamiento1,
    // tratamiento2));
    // Mockito.when(tratamientoRepository.findById(1L)).thenReturn(Optional.of(tratamiento1));
    // Mockito.when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));
    // Mockito.when(tratamientoRepository.save(Mockito.any(Tratamiento.class))).thenAnswer(i
    // -> i.getArguments()[0]);
    // }

    @Test
    public void TratamientoService_obtenerTratamientos_ReturnsAllTratamientos() {
        // 1. Arrange
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento1 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento1.setIdTratamiento(1L);
        tratamiento1.setMascota(mascota);

        Tratamiento tratamiento2 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 2", false,
                "Recomendaciones 2");
        tratamiento2.setIdTratamiento(2L);
        tratamiento2.setMascota(mascota);

        Mockito.when(tratamientoRepository.findAll()).thenReturn(Arrays.asList(tratamiento1, tratamiento2));

        // 2. Act
        List<Tratamiento> tratamientos = tratamientoService.obtenerTratamientos();

        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    @Test
    public void TratamientoService_buscarTratamientoPorId_ReturnsTratamiento() {
        // 1. Arrange
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento.setIdTratamiento(1L);
        tratamiento.setMascota(mascota);

        Mockito.when(tratamientoRepository.findById(1L)).thenReturn(Optional.of(tratamiento));

        // 2. Act
        Tratamiento foundTratamiento = tratamientoService.buscarTratamientoPorId(1L);

        // 3. Assert
        Assertions.assertThat(foundTratamiento).isNotNull();
        Assertions.assertThat(foundTratamiento.getIdTratamiento()).isEqualTo(1L);
    }

    @Test
    public void TratamientoService_addTratamiento_SavesTratamiento() {
        // 1. Arrange
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "New Tratamiento", true,
                "Recomendaciones");
        tratamiento.setMascota(mascota); // Set the mascota property

        Mockito.when(tratamientoRepository.save(Mockito.any(Tratamiento.class))).thenAnswer(i -> {
            Tratamiento savedTratamiento = i.getArgument(0);
            savedTratamiento.setIdTratamiento(1L); // Set the ID to simulate saving
            return savedTratamiento;
        });

        // 2. Act
        tratamientoService.addTratamiento(tratamiento);

        // 3. Assert
        Mockito.verify(tratamientoRepository, Mockito.times(1)).save(tratamiento);
        Assertions.assertThat(tratamiento.getIdTratamiento()).isNotNull();
        Assertions.assertThat(tratamiento.getIdTratamiento()).isEqualTo(1L);
    }

    @Test
    public void TratamientoService_deleteTratamiento_DeletesTratamiento() {
        // 1. Arrange
        // 2. Act
        tratamientoService.deleteTratamiento(1L);
        // 3. Assert
        Mockito.verify(tratamientoRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void TratamientoService_updateTratamiento_UpdatesTratamiento() {
        // 1. Arrange
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento.setIdTratamiento(1L);
        tratamiento.setMascota(mascota);

        Mockito.when(tratamientoRepository.save(Mockito.any(Tratamiento.class))).thenAnswer(i -> i.getArguments()[0]);

        tratamiento.setDescripcionTratamiento("Updated Description");

        // 2. Act
        tratamientoService.updateTratamiento(tratamiento);

        // 3. Assert
        Mockito.verify(tratamientoRepository, Mockito.times(1)).save(tratamiento);
        Assertions.assertThat(tratamiento.getDescripcionTratamiento()).isEqualTo("Updated Description");
    }

    @Test
    public void TratamientoService_getActiveTratamientos_ReturnsActiveTratamientos() {
        // 1. Arrange
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento1 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento1.setIdTratamiento(1L);
        tratamiento1.setMascota(mascota);

        Tratamiento tratamiento2 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 2", false,
                "Recomendaciones 2");
        tratamiento2.setIdTratamiento(2L);
        tratamiento2.setMascota(mascota);

        // Mocking the repository to return only active treatments
        Mockito.when(tratamientoRepository.findActiveTratamientos())
                .thenReturn(Arrays.asList(tratamiento1));

        // 2. Act
        List<Tratamiento> activeTratamientos = tratamientoService.getActiveTratamientos();

        // 3. Assert
        Assertions.assertThat(activeTratamientos).isNotNull();
        Assertions.assertThat(activeTratamientos.size()).isEqualTo(1);
        Assertions.assertThat(activeTratamientos.get(0).getIdTratamiento()).isEqualTo(1L);
    }

    @Test
    public void TratamientoService_buscarTratamientosActivosPorNombreMascota_ReturnsTratamientos() {
        // 1. Arrange
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento1 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento1.setIdTratamiento(1L);
        tratamiento1.setMascota(mascota);

        Tratamiento tratamiento2 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 2", false,
                "Recomendaciones 2");
        tratamiento2.setIdTratamiento(2L);
        tratamiento2.setMascota(mascota);

        // Mocking the repository method to return only active treatments for the given
        // pet name
        Mockito.when(tratamientoRepository.findTratamientosActivosByNombreMascota("Toby"))
                .thenReturn(Arrays.asList(tratamiento1));

        // 2. Act
        List<Tratamiento> tratamientos = tratamientoService.buscarTratamientosActivosPorNombreMascota("Toby");

        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(1);
        Assertions.assertThat(tratamientos.get(0).getIdTratamiento()).isEqualTo(1L);
    }

    @Test
    public void TratamientoService_obtenerMedicamentosActivosPorTratamiento_ReturnsMedicamentos() {
        // 1. Arrange
        Droga droga1 = new Droga("Droga A", 10.0f, 15.0f, 100, 50, "url1");
        Droga droga2 = new Droga("Droga B", 20.0f, 25.0f, 200, 100, "url2");
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento.setIdTratamiento(1L);
        tratamiento.setMascota(mascota);
        tratamiento.setDroga(Arrays.asList(droga1, droga2));

        Mockito.when(tratamientoRepository.findById(1L)).thenReturn(Optional.of(tratamiento));

        // 2. Act
        List<Droga> drogas = tratamientoService.obtenerMedicamentosActivosPorTratamiento(1L);

        // 3. Assert
        Assertions.assertThat(drogas).isNotNull();
        Assertions.assertThat(drogas.size()).isEqualTo(2);
    }

    @Test
    public void TratamientoService_getMedicamentosPorTratamiento_ReturnsMedicamentos() {
        // 1. Arrange
        Droga droga1 = new Droga("Droga A", 10.0f, 15.0f, 100, 50, "url1");
        Droga droga2 = new Droga("Droga B", 20.0f, 25.0f, 200, 100, "url2");
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento.setIdTratamiento(1L);
        tratamiento.setMascota(mascota);
        tratamiento.setDroga(Arrays.asList(droga1, droga2));

        Mockito.when(tratamientoRepository.findById(1L)).thenReturn(Optional.of(tratamiento));

        // 2. Act
        List<Droga> drogas = tratamientoService.getMedicamentosPorTratamiento(1L);

        // 3. Assert
        Assertions.assertThat(drogas).isNotNull();
        Assertions.assertThat(drogas.size()).isEqualTo(2);
    }

    @Test
    public void TratamientoService_updateMedicamentosDelTratamiento_UpdatesMedicamentos() {
        // 1. Arrange
        Droga droga1 = new Droga("Droga A", 10.0f, 15.0f, 100, 50, "url1");
        Droga droga2 = new Droga("Droga B", 20.0f, 25.0f, 200, 100, "url2");
        Droga droga3 = new Droga("Droga C", 30.0f, 35.0f, 300, 150, "url3");
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento.setIdTratamiento(1L);
        tratamiento.setMascota(mascota);
        tratamiento.setDroga(Arrays.asList(droga1, droga2));

        Mockito.when(tratamientoRepository.findById(1L)).thenReturn(Optional.of(tratamiento));
        Mockito.when(tratamientoRepository.save(Mockito.any(Tratamiento.class))).thenAnswer(i -> i.getArguments()[0]);

        List<Droga> newDrogas = Arrays.asList(droga3);

        // 2. Act
        tratamientoService.updateMedicamentosDelTratamiento(1L, newDrogas);

        // 3. Assert
        Mockito.verify(tratamientoRepository, Mockito.times(1)).save(tratamiento);
        Assertions.assertThat(tratamiento.getDroga().size()).isEqualTo(1);
        Assertions.assertThat(tratamiento.getDroga().get(0).getIdDroga()).isEqualTo(droga3.getIdDroga());
    }

    @Test
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
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento1 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento1.setIdTratamiento(1L);
        tratamiento1.setMascota(mascota);

        Tratamiento tratamiento2 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 2", false,
                "Recomendaciones 2");
        tratamiento2.setIdTratamiento(2L);
        tratamiento2.setMascota(mascota);

        Mockito.when(tratamientoRepository.findByMascotaId(1L)).thenReturn(Arrays.asList(tratamiento1, tratamiento2));

        // 2. Act
        List<Tratamiento> tratamientos = tratamientoService.findTratamientosByMascotaId(1L);

        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    @Test
    public void TratamientoService_addTratamientoToMascota_AddsTratamiento() {
        // 1. Arrange
        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);
        Tratamiento tratamiento = new Tratamiento(Date.valueOf(LocalDate.now()), "New Tratamiento", true,
                "Recomendaciones");
        tratamiento.setMascota(mascota); // Set the mascota property

        Mockito.when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));
        Mockito.when(tratamientoRepository.save(Mockito.any(Tratamiento.class))).thenAnswer(i -> i.getArguments()[0]);

        // 2. Act
        boolean result = tratamientoService.addTratamientoToMascota(1L, tratamiento);

        // 3. Assert
        Assertions.assertThat(result).isTrue();
        Mockito.verify(tratamientoRepository, Mockito.times(1)).save(tratamiento);
    }

    @Test
    public void TratamientoService_getTratamientosByVeterinario_ReturnsTratamientos() {
        // 1. Arrange
        Veterinario veterinario = new Veterinario();
        veterinario.setIdVeterinario(1L);

        Mascota mascota = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                Date.valueOf(LocalDate.now()), true);

        Tratamiento tratamiento1 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 1", true,
                "Recomendaciones 1");
        tratamiento1.setIdTratamiento(1L);
        tratamiento1.setMascota(mascota);
        tratamiento1.setVeterinario(veterinario);

        Tratamiento tratamiento2 = new Tratamiento(Date.valueOf(LocalDate.now()), "Tratamiento 2", true,
                "Recomendaciones 2");
        tratamiento2.setIdTratamiento(2L);
        tratamiento2.setMascota(mascota);
        tratamiento2.setVeterinario(veterinario);

        Mockito.when(tratamientoRepository.findTratamientosByVeterinario(1L))
                .thenReturn(Arrays.asList(tratamiento1, tratamiento2));

        // 2. Act
        List<Tratamiento> tratamientos = tratamientoService.getTratamientosByVeterinario(1L);

        // 3. Assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }
}
