package com.web.dpelos.service;

import java.util.List;

import com.web.dpelos.entity.Enfermedad;

public interface EnfermedadService {
    public Enfermedad buscarEnfermedadPorId (Long id);

    public List<Enfermedad> obtenerEnfermedades();

    public void addEnfermedad(Enfermedad enfermedad);

    public void deleteEnfermedad(Long id);

    public void updateEnfermedad(Enfermedad raza);
}
