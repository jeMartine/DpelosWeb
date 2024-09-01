package com.web.dpelos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RazaMascota {
    @Id
    @GeneratedValue
    private Long idRazaMascota;
    private String razaMascota;
    public RazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }
}
