package com.web.dpelos.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veterinario {
    @Id
    @GeneratedValue
    private Long idVeterinario;
    private String nombreVeterinario;
    private String apellidoVeterinario;
    private String cedulaVeterinario;
    //@JsonIgnore
    private String passwordVeterinario;
    private String fotoUrl;
    private int numeroAtenciones;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "veterinario")
    private List<Tratamiento> tratamientos = new ArrayList();


    public Veterinario(String nombreVeterinario, String apellidoVeterinario, 
            String cedulaVeterinario, String passwordVeterinario, String fotoUrl, 
            int numeroAtenciones) {
        this.nombreVeterinario = nombreVeterinario;
        this.apellidoVeterinario = apellidoVeterinario;
        this.cedulaVeterinario = cedulaVeterinario;
        this.passwordVeterinario = passwordVeterinario;
        this.fotoUrl = fotoUrl;
        this.numeroAtenciones = numeroAtenciones;
    }
}
