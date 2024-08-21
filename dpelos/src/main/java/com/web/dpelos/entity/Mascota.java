package com.web.dpelos.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
/*Plain Old Java Object */
public class Mascota {
    /* Atributos de la Clase por el momento */
    @Id
    @GeneratedValue
    private Long idMascota;
    private String nombreMascota;
    private Integer edadMascota;
    private String urlFotoMascota;
    private String razaMascota;
    private Integer idDueno;
    private Date fechaCreacion;
    private boolean estado;
    private String enfermedad;

    //constructor sin id
    public Mascota(String nombreMascota, Integer edadMascota, String urlFotoMascota,
            String razaMascota, Integer idDueno, Date fechaCreacion, boolean estado, String enfermedad) {
        this.nombreMascota = nombreMascota;
        this.edadMascota = edadMascota;
        this.urlFotoMascota = urlFotoMascota;
        this.razaMascota = razaMascota;
        this.idDueno = idDueno;
        this.fechaCreacion= fechaCreacion;
        this.estado = estado;
        this.enfermedad = enfermedad;
    }
}