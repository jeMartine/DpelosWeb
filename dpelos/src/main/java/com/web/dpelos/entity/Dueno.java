package com.web.dpelos.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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

    @JsonIgnore
    @OneToMany(mappedBy = "dueno")
    private List<Mascota> mascotas = new ArrayList();

    // constructor sin id
    public Dueno(String cedulaDueno, String nombreDueno, String apellidoDueno,
            String correoDueno, String celularDueno, String fotoUrl) {
        this.cedulaDueno = cedulaDueno;
        this.nombreDueno = nombreDueno;
        this.apellidoDueno = apellidoDueno;
        this.correoDueno = correoDueno;
        this.celularDueno = celularDueno;
        this.fotoUrl = fotoUrl;
    }

    public Long getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Long idDueno) {
        this.idDueno = idDueno;
    }

    public String getCedulaDueno() {
        return cedulaDueno;
    }

    public void setCedulaDueno(String cedulaDueno) {
        this.cedulaDueno = cedulaDueno;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getApellidoDueno() {
        return apellidoDueno;
    }

    public void setApellidoDueno(String apellidoDueno) {
        this.apellidoDueno = apellidoDueno;
    }

    public String getCorreoDueno() {
        return correoDueno;
    }

    public void setCorreoDueno(String correoDueno) {
        this.correoDueno = correoDueno;
    }

    public String getCelularDueno() {
        return celularDueno;
    }

    public void setCelularDueno(String celularDueno) {
        this.celularDueno = celularDueno;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}