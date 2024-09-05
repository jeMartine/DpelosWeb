package com.web.dpelos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Enfermedad {
    @Id
    @GeneratedValue
    private Long idEnfermedad;
    private String nombreEnfermedad;
    
    public Enfermedad(String nombreEnfermedad) {
        this.nombreEnfermedad = nombreEnfermedad;
    }
}


