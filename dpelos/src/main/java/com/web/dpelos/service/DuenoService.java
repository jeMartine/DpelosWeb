package com.web.dpelos.service;

import java.util.Collection;
import java.util.List;

import com.web.dpelos.entity.Dueno;

public interface DuenoService {
    public Dueno buscarDuenoPorId(Long id);

    public List<Dueno> obtenerDuenos();

    public void addDueno(Dueno dueno);

    public void deleteDueno(Long id);

    public void updateDueno(Dueno dueno);

    public Dueno buscarDuenoPorCedula(String cedulaDueno);

}
