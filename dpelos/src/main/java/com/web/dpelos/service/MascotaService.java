package com.web.dpelos.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.dpelos.entity.Mascota;

/*Interfaz con la generalización de los métodos*/
public interface MascotaService {
    public Mascota buscarMascotaPorId(Long id);

    public List<Mascota> obtenerMascotas();

    public List<Mascota> obtenerMascotasDelDueno(Long idDueno);

    public Mascota addMascota(Mascota mascota);

    public void deleteMascota(Long id);

    public Mascota updateMascota(Mascota mascota);

    public Page<Mascota> buscarMascotasPorNombre(String nombreMascota, int page, int size);

    public Page<Mascota> getMascotasPaginadas(int page, int size);

    public long obtenerTotalMascotas();

    public long obtenerTotalMascotasActivas();

}
