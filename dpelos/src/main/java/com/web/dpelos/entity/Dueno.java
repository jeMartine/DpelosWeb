package com.web.dpelos.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @GeneratedValue()
    private Long idDueno;
    private String cedulaDueno;
    private String nombreDueno;
    private String apellidoDueno;
    private String correoDueno;
    private String celularDueno;
    private String fotoUrl;

    @OneToMany(mappedBy = "dueno")
    private List<Mascota> mascotas = new ArrayList();
    


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