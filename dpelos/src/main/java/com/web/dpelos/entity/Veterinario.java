package com.web.dpelos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Veterinario {
    @Id
    @GeneratedValue
    private Long idVeterinario;
    private String nombreVeterinario;
    private String apellidoVeterinario;
    private String cedulaVeterinario;
    private String passwordVeterinario;
    private String urlFotoVeterinario;
    private int numeroAtenciones;
    private Long idEspecialidad;
 
    public Veterinario(String nombreVeterinario, String apellidoVeterinario, 
            String cedulaVeterinario, String passwordVeterinario, String urlFotoVeterinario, 
            int numeroAtenciones, Long idEspecialidad) {
        this.nombreVeterinario = nombreVeterinario;
        this.apellidoVeterinario = apellidoVeterinario;
        this.cedulaVeterinario = cedulaVeterinario;
        this.passwordVeterinario = passwordVeterinario;
        this.urlFotoVeterinario = urlFotoVeterinario;
        this.numeroAtenciones = numeroAtenciones;
        this.idEspecialidad = idEspecialidad;
    }
}
