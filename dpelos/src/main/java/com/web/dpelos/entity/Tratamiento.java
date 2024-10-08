package com.web.dpelos.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTratamiento;

    private Date fechaAdministracion;
    private String descripcionTratamiento;

    @ManyToOne
    @JoinColumn(name = "droga_id")
    private Droga droga;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mascota_id", nullable = true)
    private Mascota mascota;    

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    // public Tratamiento(Date fechaAdministracion, Droga droga, Mascota mascota,
    // Veterinario veterinario, String descripcionTratamiento) {
    // this.fechaAdministracion = fechaAdministracion;
    // this.descripcionTratamiento = descripcionTratamiento;
    // }
    public Tratamiento(Date fechaAdministracion, String descripcionTratamiento) {
        this.fechaAdministracion = fechaAdministracion;
        this.descripcionTratamiento = descripcionTratamiento;
    }
}
