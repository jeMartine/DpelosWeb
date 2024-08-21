package com.web.dpelos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Clase Dueno */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dueno {
    @Id
    @GeneratedValue
    private Long idDueno;
    private String cedulaDueno;
    private String nombreDueno;
    private String apellidoDueno;
    private String correoDueno;
    private String celularDueno;
    private String fotoUrl;

    //constructor sin id
    public Dueno(String cedulaDueno, String nombreDueno, String apellidoDueno, 
                String correoDueno, String celularDueno, String fotoUrl) {
        this.cedulaDueno = cedulaDueno;
        this.nombreDueno = nombreDueno;
        this.apellidoDueno = apellidoDueno;
        this.correoDueno = correoDueno;
        this.celularDueno = celularDueno;
        this.fotoUrl=fotoUrl;
    }
}