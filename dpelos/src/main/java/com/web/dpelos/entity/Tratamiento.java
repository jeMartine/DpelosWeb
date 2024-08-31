package com.web.dpelos.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
    private Long idTratamiento;
    private Date fechaAdministracion;
    private String descripcionTratamiento;

    @ManyToOne
    @JoinColumn(name = "droga_id")
    private Droga droga;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    public Tratamiento(Date fechaAdministracion, Droga droga, Mascota mascota, Veterinario veterinario, String descripcionTratamiento) {
        this.fechaAdministracion = fechaAdministracion;
        this.descripcionTratamiento = descripcionTratamiento;
    }
}
