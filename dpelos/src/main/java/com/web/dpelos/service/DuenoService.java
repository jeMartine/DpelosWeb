package com.web.dpelos.service;

import java.util.Collection;

import com.web.dpelos.entity.Dueno;

public interface DuenoService {
    public Dueno buscarDuenoPorId(Long id);

    public Collection<Dueno> obtenerDuenos();

    public void addDueno(Dueno dueno);

    public void deleteDueno(Long id);

    public void updateDueno(Dueno dueno);

}
