package com.web.dpelos.service;

import java.util.List;

import com.web.dpelos.entity.Raza;

public interface RazaService {
    public Raza buscarRazaPorId (Long id);

    public List<Raza> obtenerRazas();

    public Raza addRaza(Raza raza);

    public void deleteRaza(Long id);

    public Raza updateRaza(Raza raza);
}
