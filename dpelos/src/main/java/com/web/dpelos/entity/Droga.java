package com.web.dpelos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Droga {
    @Id
    @GeneratedValue
    private Long idDroga;
    private String nombreDroga;
    private float precioCompra;
    private float precioVenta;
    private int unitDisponibles;
    private int unitVendidas;
    private String urlFotoDroga;

    public Droga(String nombreDroga, float precioCompra, float precioVenta, int unitDisponibles, int unitVendidas, String urlFotoDroga) {
        this.nombreDroga = nombreDroga;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.unitDisponibles = unitDisponibles;
        this.unitVendidas = unitVendidas;
        this.urlFotoDroga = urlFotoDroga;
    }
}
