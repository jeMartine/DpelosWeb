package com.web.dpelos.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    String nombrePropietario;
    String nombreMascota;
    String correoPropietario;
    String fechaConsulta;
    String servicio;
}
