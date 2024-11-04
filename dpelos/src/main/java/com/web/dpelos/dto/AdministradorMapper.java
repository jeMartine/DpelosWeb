package com.web.dpelos.dto;

import org.mapstruct.factory.Mappers;

import com.web.dpelos.entity.Administrador;

public interface AdministradorMapper {
    AdministradorMapper INSTACE = Mappers.getMapper(AdministradorMapper.class);

    AdministradorDTO convert (Administrador administrador);
}
