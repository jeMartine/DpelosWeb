package com.web.dpelos.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Veterinario {

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @Id
    @GeneratedValue
    private Long idVeterinario;
    private String nombreVeterinario;
    private String apellidoVeterinario;
    private String cedulaVeterinario;
    private boolean estadoVeterinario;
    // @JsonIgnore
    private String fotoUrl;
    private int numeroAtenciones;
    @Transient
    private String passwordVeterinario;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "veterinario")
    private List<Tratamiento> tratamientos = new ArrayList();

    public Veterinario(String nombreVeterinario, String apellidoVeterinario,
            String cedulaVeterinario, String passwordVeterinario, String fotoUrl,
            int numeroAtenciones, boolean estadoVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
        this.apellidoVeterinario = apellidoVeterinario;
        this.cedulaVeterinario = cedulaVeterinario;
        this.passwordVeterinario = passwordVeterinario;
        this.fotoUrl = fotoUrl;
        this.numeroAtenciones = numeroAtenciones;
        this.estadoVeterinario = estadoVeterinario;
    }

}
