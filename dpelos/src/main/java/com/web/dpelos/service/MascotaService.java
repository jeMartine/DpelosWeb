package com.web.dpelos.service;

import java.util.Collection;

import com.web.dpelos.entity.Mascota;

public interface MascotaService {
    public Mascota buscarMascotaPorId(Integer id);
    public Collection<Mascota> obtenerMascotas();
}
