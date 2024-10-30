package com.web.dpelos.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.dpelos.entity.Dueno;
import com.web.dpelos.repository.AdminRepository;
import com.web.dpelos.repository.DrogaRepository;
import com.web.dpelos.repository.DuenoRepository;
import com.web.dpelos.repository.EnfermedadMascotaRepository;
import com.web.dpelos.repository.EspecialidadRepository;
import com.web.dpelos.repository.MascotaRepository;
import com.web.dpelos.repository.RazaMascotaRepository;
import com.web.dpelos.repository.TratamientoRepository;
import com.web.dpelos.repository.VeterinarioRepository;
import com.web.dpelos.service.DuenoService;

@WebMvcTest(controllers = DuenoController.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class DuenoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DuenoService duenoService;

    @MockBean
    private DrogaRepository drogaRepository;

    @MockBean
    private VeterinarioRepository veterinarioRepository;

    @MockBean
    private EnfermedadMascotaRepository enfermedadMascotaRepository;

    @MockBean
    private EspecialidadRepository especialidadRepository;

    @MockBean
    private AdminRepository adminRepository;

    @MockBean
    private MascotaRepository mascotaRepository;

    @MockBean
    private DuenoRepository duenoRepository;
    @MockBean
    private RazaMascotaRepository razaMascotaRepository;
    @MockBean
    private TratamientoRepository tratamientoRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void DuenoController_listaDuenos_ReturnsListOfDuenos() throws Exception {
        // Arrange
        Dueno dueno1 = new Dueno("13231", "Ana", "Martínez", "anamar@gmail.com", "3001234567", "https://static01.n");
        Dueno dueno2 = new Dueno("13232", "Juan", "Pérez", "juanp@gmail.com", "3001234568", "https://static02.n");
        List<Dueno> duenos = Arrays.asList(dueno1, dueno2);
        when(duenoService.obtenerDuenos()).thenReturn(duenos);

        // Act & Assert
        ResultActions response = mockMvc.perform(get("/dueno"));
        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].cedulaDueno").value(dueno1.getCedulaDueno()))
                .andExpect(jsonPath("$[1].cedulaDueno").value(dueno2.getCedulaDueno()));
    }

    @Test
    public void DuenoController_getDuenoById_ReturnsDueno() throws Exception {
        // Arrange
        Dueno dueno = new Dueno("13231", "Ana", "Martínez", "anamar@gmail.com", "3001234567", "https://static01.n");
        when(duenoService.buscarDuenoPorId(1L)).thenReturn(dueno);

        // Act & Assert
        ResultActions response = mockMvc.perform(get("/dueno/1"));
        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.cedulaDueno").value(dueno.getCedulaDueno()));
    }

    @Test
    public void DuenoController_addDueno_Dueno() throws Exception {
        // Arrange
        Dueno dueno = new Dueno("13231", "Ana", "Martínez", "anamar@gmail.com", "3001234567", "https://static01.n");
        when(duenoService.addDueno(dueno)).thenReturn(dueno);

        // Act & Assert
        ResultActions response = mockMvc.perform(
                post("/dueno")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dueno)));
        response.andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.cedulaDueno").value(dueno.getCedulaDueno()));
    }

    @Test
    public void DuenoController_deleteDueno_DeletesDueno() throws Exception {
        // Arrange
        Long id = 1L;

        // Act & Assert
        ResultActions response = mockMvc.perform(delete("/dueno/delete/" + id));
        response.andExpect(status().isNoContent())
                .andExpect(content().string("Dueño eliminado exitosamente"));
    }

    @Test
    public void DuenoController_mostrarDuenoActualizado_UpdatesDueno() throws Exception {
        // Arrange
        Dueno dueno = new Dueno("13231", "Ana", "Martínez", "anamar@gmail.com", "3001234567", "https://static01.n");
        when(duenoService.updateDueno(dueno)).thenReturn(dueno);

        // Act & Assert
        ResultActions response = mockMvc.perform(
                put("/dueno/update")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dueno)));
        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.cedulaDueno").value(dueno.getCedulaDueno()));
    }

    @Test
    public void DuenoController_buscarDueno_ReturnsDueno() throws Exception {
        // Arrange
        Dueno dueno = new Dueno("13231", "Ana", "Martínez", "anamar@gmail.com", "3001234567", "https://static01.n");
        when(duenoService.buscarDuenoPorCedula("13231")).thenReturn(dueno);

        // Act & Assert
        ResultActions response = mockMvc.perform(get("/dueno/buscarCedula/13231"));
        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.cedulaDueno").value(dueno.getCedulaDueno()));
    }
}