package com.web.dpelos.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTratamiento;
    private boolean estado;
    private Date fechaAdministracion;
    private String descripcionTratamiento;
    private String recomendacionesGenerales;

    @ManyToMany
    @JoinTable(name = "tratamiento_droga", joinColumns = @JoinColumn(name = "tratamiento_droga"), inverseJoinColumns = @JoinColumn(name = "droga_id"))
    private List<Droga> drogas = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "mascota_id", nullable = true)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    // public Tratamiento(Date fechaAdministracion, Droga droga, Mascota mascota,
    // Veterinario veterinario, String descripcionTratamiento) {
    // this.fechaAdministracion = fechaAdministracion;
    // this.descripcionTratamiento = descripcionTratamiento;
    // }
    public Tratamiento(Date fechaAdministracion, String descripcionTratamiento, boolean estado,
            String recomendacionesGenerales) {
        this.recomendacionesGenerales = recomendacionesGenerales;
        this.estado = estado;
        this.fechaAdministracion = fechaAdministracion;
        this.descripcionTratamiento = descripcionTratamiento;
    }

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaAdministracion() {
        return fechaAdministracion;
    }

    public void setFechaAdministracion(Date fechaAdministracion) {
        this.fechaAdministracion = fechaAdministracion;
    }

    public String getDescripcionTratamiento() {
        return descripcionTratamiento;
    }

    public void setDescripcionTratamiento(String descripcionTratamiento) {
        this.descripcionTratamiento = descripcionTratamiento;
    }

    public String getRecomendacionesGenerales() {
        return recomendacionesGenerales;
    }

    public void setRecomendacionesGenerales(String recomendacionesGenerales) {
        this.recomendacionesGenerales = recomendacionesGenerales;
    }

    public List<Droga> getDroga() {
        return drogas;
    }

    public void setDroga(List<Droga> drogas) {
        this.drogas = drogas;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

}
