package com.web.dpelos.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veterinario {
    @Id
    @GeneratedValue
    private Long idVeterinario;
    private String nombreVeterinario;
    private String apellidoVeterinario;
    private String cedulaVeterinario;
    private boolean estadoVeterinario;
    // @JsonIgnore
    private String passwordVeterinario;
    private String fotoUrl;
    private int numeroAtenciones;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "veterinario")
    private List<Tratamiento> tratamientos = new ArrayList();

    public Veterinario(String nombreVeterinario, String apellidoVeterinario,
            String cedulaVeterinario, String passwordVeterinario, String fotoUrl,
            int numeroAtenciones, boolean estadoVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
        this.apellidoVeterinario = apellidoVeterinario;
        this.cedulaVeterinario = cedulaVeterinario;
        this.passwordVeterinario = passwordVeterinario;
        this.fotoUrl = fotoUrl;
        this.numeroAtenciones = numeroAtenciones;
        this.estadoVeterinario = estadoVeterinario;
    }

    public Long getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Long idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombreVeterinario() {
        return nombreVeterinario;
    }

    public void setNombreVeterinario(String nombreVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
    }

    public String getApellidoVeterinario() {
        return apellidoVeterinario;
    }

    public void setApellidoVeterinario(String apellidoVeterinario) {
        this.apellidoVeterinario = apellidoVeterinario;
    }

    public String getCedulaVeterinario() {
        return cedulaVeterinario;
    }

    public void setCedulaVeterinario(String cedulaVeterinario) {
        this.cedulaVeterinario = cedulaVeterinario;
    }

    public boolean isEstadoVeterinario() {
        return estadoVeterinario;
    }

    public void setEstadoVeterinario(boolean estadoVeterinario) {
        this.estadoVeterinario = estadoVeterinario;
    }

    public String getPasswordVeterinario() {
        return passwordVeterinario;
    }

    public void setPasswordVeterinario(String passwordVeterinario) {
        this.passwordVeterinario = passwordVeterinario;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public int getNumeroAtenciones() {
        return numeroAtenciones;
    }

    public void setNumeroAtenciones(int numeroAtenciones) {
        this.numeroAtenciones = numeroAtenciones;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }
}
