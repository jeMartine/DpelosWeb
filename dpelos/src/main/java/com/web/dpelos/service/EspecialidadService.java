package com.web.dpelos.service;

import java.util.List;

import com.web.dpelos.entity.Especialidad;

public interface EspecialidadService {
    public Especialidad buscarEspecialidadPorId(Long id);

    public List<Especialidad> obtenerEspecialidades();

}
