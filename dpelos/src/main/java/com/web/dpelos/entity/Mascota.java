package com.web.dpelos.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private Date fechaCreacion;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "dueno_id")
    private Dueno dueno;

    @ManyToOne
    @JoinColumn(name = "raza_id")
    private Raza raza;

    @ManyToOne
    @JoinColumn(name = "enfermedad_id")
    private Enfermedad enfermedad;

    @OneToMany
    @JoinColumn(name = "tratamiento_id")
    private List<Tratamiento> tratamientos = new ArrayList();

    //constructor sin id
    public Mascota(String nombreMascota, Integer edadMascota, String urlFotoMascota,
             Date fechaCreacion, boolean estado) {
        this.nombreMascota = nombreMascota;
        this.edadMascota = edadMascota;
        this.urlFotoMascota = urlFotoMascota;
        this.fechaCreacion= fechaCreacion;
        this.estado = estado;
    }
}