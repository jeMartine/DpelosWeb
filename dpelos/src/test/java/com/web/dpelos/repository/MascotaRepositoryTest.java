package com.web.dpelos.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Enfermedad;
import com.web.dpelos.entity.Mascota;
import com.web.dpelos.entity.Raza;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class MascotaRepositoryTest {
    // Repositorios para los tests.
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private DuenoRepository duenoRepository;
    @Autowired
    private RazaMascotaRepository razaMascotaRepository;
    @Autowired
    private EnfermedadMascotaRepository enfermedadMascotaRepository;

    // Instancias de dataos para los tests.
    @BeforeEach
    public void setUp() {
        // Dueños tests..
        Dueno dueno1 = new Dueno("13231", "Ana", "Martínez", "ana.martinez@gmail.com",
                "3001234567",
                "https://static01.nyt.com/images/2017/05/07/arts/07GAL-GADOTweb/07GAL-GADOTweb-articleLarge.jpg?quality=75&auto=webp&disable=upscale");
        Dueno dueno2 = new Dueno("210332323", "Jorge", "Esteban", "jorge.esteban@gmail.com", "3122345678",
                "https://media.gq.com.mx/photos/6203c5ba43f71a078a355054/16:9/w_2560%2Cc_limit/atractivo.jpg");
        duenoRepository.save(dueno1);
        duenoRepository.save(dueno2);
        // Mascotas para los tests..
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        Mascota masc1 = new Mascota("Toby", 3,
                "https://images.saymedia-content.com/.image/t_share/MjAxMjg4MjkxNjI5MTQ3Njc1/labrador-retriever-guide.jpg",
                sqlDate, true);

        Mascota masc2 = new Mascota("Rex", 4,
                "https://i.pinimg.com/1200x/07/84/ab/0784ab30e8c3c18e909620e531572b53.jpg",
                sqlDate,
                true);
        // Razas para los tests.
        Raza raza1 = new Raza("Husky");
        Raza raza2 = new Raza("Labrador");
        razaMascotaRepository.save(raza1);
        razaMascotaRepository.save(raza2);
        // Enfermedades para los tests.
        Enfermedad enfer1 = new Enfermedad("Parvovirus");
        Enfermedad enfer2 = new Enfermedad("Rabia");
        enfermedadMascotaRepository.save(enfer1);
        enfermedadMascotaRepository.save(enfer2);
        // Asociando las mascotas con los dueños, razas y enfermedades.
        masc1.setDueno(dueno1);
        masc1.setRaza(raza1);
        masc1.setEnfermedad(enfer1);
        mascotaRepository.save(masc1);

        masc2.setDueno(dueno2);
        masc2.setRaza(raza2);
        masc2.setEnfermedad(enfer2);
        mascotaRepository.save(masc2);
    }

    // Test 1
    @Test
    public void MascotaRepository_save_Mascota() {
        // 1. Arrange
        LocalDate date = LocalDate.now();
        Date sqlDate = Date.valueOf(date);
        Mascota mascota = new Mascota("Firulais", 5, "", sqlDate, true);
        // 2. Act
        Mascota mascotaSaved = mascotaRepository.save(mascota);
        // 3. Assert
        Assertions.assertThat(mascotaSaved).isNotNull();
    }

    // Test 2
    @Test
    public void MascotaRepository_FindAll_NotEmptyList() {
        // 1. Arrange
        // 2. Act
        List<Mascota> mascotas = mascotaRepository.findAll();
        // 3. Assert
        Assertions.assertThat(mascotas).isNotNull();
        Assertions.assertThat(mascotas.size()).isEqualTo(2);
        Assertions.assertThat(mascotas.get(0).getNombreMascota()).isEqualTo("Toby");
    }

    // Test 3
    @Test
    public void MascotaRepository_update_Mascota() {
        // 1. Arrange
        Mascota mascota = mascotaRepository.findById(1L).get();
        mascota.setNombreMascota("Firulais");
        // 2. Act
        Mascota mascotaUpdated = mascotaRepository.save(mascota);
        // 3. Assert
        Assertions.assertThat(mascotaUpdated).isNotNull();
        Assertions.assertThat(mascotaUpdated.getNombreMascota()).isEqualTo("Firulais");
    }

    // Test 4
    @Test
    public void MascotaRepository_delete_Mascota() {
        // 1. Arrange
        Mascota mascota = mascotaRepository.findById(1L).get();
        // 2. Act
        mascotaRepository.delete(mascota);
        // 3. Assert
        Assertions.assertThat(mascotaRepository.findById(1L)).isEmpty();
    }

    // Test 5
    // 1 Custom
    @Test
    public void MascotaRepository_findByIdDueno_ReturnsMascotas() {
        // 1. Arrange
        Dueno dueno = duenoRepository.findById(1L).get();

        // 2. Act
        Collection<Mascota> mascotas = mascotaRepository.findByIdDueno(dueno);

        // 3. Assert
        Assertions.assertThat(mascotas).isNotNull();
        Assertions.assertThat(mascotas.size()).isEqualTo(1);
        Assertions.assertThat(mascotas.iterator().next().getNombreMascota()).isEqualTo("Toby");
    }

    // Test 6
    // 2 Custom
    @Test
    public void MascotaRepository_findByIdDueno_NoMascotas() {
        // 1. Arrange
        Dueno dueno = new Dueno("99999", "Nonexistent", "User", "nonexistent.user@gmail.com", "0000000000", "");
        duenoRepository.save(dueno);

        // 2. Act
        Collection<Mascota> mascotas = mascotaRepository.findByIdDueno(dueno);

        // 3. Assert
        Assertions.assertThat(mascotas).isNotNull();
        Assertions.assertThat(mascotas).isEmpty();
    }

    // Test 7
    // 3 Custom
    @Test
    public void MascotaRepository_countByEstadoTrue_NoActiveMascotas() {
        // 1. Arrange
        Mascota mascota1 = mascotaRepository.findById(1L).get();
        Mascota mascota2 = mascotaRepository.findById(2L).get();
        mascota1.setEstado(false);
        mascota2.setEstado(false);
        mascotaRepository.save(mascota1);
        mascotaRepository.save(mascota2);

        // 2. Act
        long count = mascotaRepository.countByEstadoTrue();

        // 3. Assert
        Assertions.assertThat(count).isEqualTo(0);
    }

    // Test 8
    // 4 Custom
    @Test
    public void MascotaRepository_countByEstadoTrue_AllActiveMascotas() {
        // 1. Arrange
        // 2. Act
        long count = mascotaRepository.countByEstadoTrue();
        // 3. Assert
        Assertions.assertThat(count).isEqualTo(2);
    }

    // Test 9
    // 5 Custom
    @Test
    public void MascotaRepository_countByEstadoTrue_MixedEstadoMascotas() {
        // 1. Arrange
        Mascota mascota1 = mascotaRepository.findById(1L).get();
        mascota1.setEstado(false);
        mascotaRepository.save(mascota1);
        // 2. Act
        long count = mascotaRepository.countByEstadoTrue();
        // 3. Assert
        Assertions.assertThat(count).isEqualTo(1);
    }

}