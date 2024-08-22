package com.web.dpelos.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
/*Plain Old Java Object */
public class Mascota {
    /* Atributos de la Clase por el momento */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;
    private String nombreMascota;
    private Integer edadMascota;
    private String urlFotoMascota;
    private String razaMascota;
    private Date fechaCreacion;
    private boolean estado;
    private String enfermedad;

    @ManyToOne
    @JoinColumn(name = "dueno_id")
    private Dueno dueno;

    //constructor sin id
    public Mascota(String nombreMascota, Integer edadMascota, String urlFotoMascota,
            String razaMascota, Date fechaCreacion, boolean estado, String enfermedad) {
        this.nombreMascota = nombreMascota;
        this.edadMascota = edadMascota;
        this.urlFotoMascota = urlFotoMascota;
        this.razaMascota = razaMascota;
        this.fechaCreacion= fechaCreacion;
        this.estado = estado;
        this.enfermedad = enfermedad;
    }
}