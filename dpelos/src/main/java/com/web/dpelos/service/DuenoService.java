package com.web.dpelos.service;

import java.util.Collection;

import com.web.dpelos.entity.Dueno;

public interface DuenoService {
    public Dueno buscarDuenoPorId(Integer id);

    public Collection<Dueno> obtenerDuenos();

    public void addDueno(Dueno dueno);

    public void deleteDueno(Integer id);

    public void updateDueno(Dueno dueno);

}
