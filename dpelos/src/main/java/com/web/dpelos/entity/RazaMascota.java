package com.web.dpelos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RazaMascota {
    @Id
    @GeneratedValue
    private Long idRazaMascota;
    private String nombreRazaMascota;
    public RazaMascota(String nombreRazaMascota) {
        this.nombreRazaMascota = nombreRazaMascota;
    }
}
