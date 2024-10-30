package com.web.dpelos.service;

import java.util.List;

import com.web.dpelos.entity.Dueno;

public interface DuenoService {
    public Dueno buscarDuenoPorId(Long id);

    public List<Dueno> obtenerDuenos();

    public Dueno addDueno(Dueno dueno);

    public void deleteDueno(Long id);

    public Dueno updateDueno(Dueno dueno);

    public Dueno buscarDuenoPorCedula(String cedulaDueno);

    

}
