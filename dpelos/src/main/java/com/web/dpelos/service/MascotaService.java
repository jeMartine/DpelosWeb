package com.web.dpelos.service;

import java.util.Collection;

import com.web.dpelos.entity.Mascota;
/*Interfaz con la generalización de los métodos*/
public interface MascotaService {
    public Mascota buscarMascotaPorId(Integer id);
    public Collection<Mascota> obtenerMascotas();
    public void addMascota(Mascota mascota);
    public void deleteMascota(Integer id);
    public void updateMascota(Mascota mascota);
}
