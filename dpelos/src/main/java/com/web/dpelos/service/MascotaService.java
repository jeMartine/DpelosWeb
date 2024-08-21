package com.web.dpelos.service;

import java.util.Collection;

import com.web.dpelos.entity.Mascota;
/*Interfaz con la generalización de los métodos*/
public interface MascotaService {
    public Mascota buscarMascotaPorId(Long id);
    public Collection<Mascota> obtenerMascotas();
    public void addMascota(Mascota mascota);
    public void deleteMascota(Long id);
    public void updateMascota(Mascota mascota);
}
