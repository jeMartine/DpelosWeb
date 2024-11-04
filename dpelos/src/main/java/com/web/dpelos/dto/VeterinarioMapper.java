package com.web.dpelos.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.web.dpelos.entity.Especialidad;
import com.web.dpelos.entity.Veterinario;

@Mapper
public interface VeterinarioMapper {
    VeterinarioMapper INSTACE = Mappers.getMapper(VeterinarioMapper.class);

    VeterinarioDTO convert(Veterinario veterinario);

    default String map(Especialidad especialidad) {
        return especialidad != null ? especialidad.getNombreEspecialidad() : null;
    }
}
