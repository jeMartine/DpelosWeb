package com.web.dpelos.service;

import java.util.List;

import com.web.dpelos.dto.AdministradorDTO;
import com.web.dpelos.entity.Administrador;

public interface AdminService{
     public AdministradorDTO toDTO(Administrador administrador);
     
    public Administrador buscarAdministradorPorId(Long id);

    public List<Administrador> obtenerAdministradors();

    public void addAdministrador(Administrador admin);

    public void deleteAdministrador(Long id);

    public void updateAdministrador(Administrador admin);

    public Administrador buscarAdminPorCedula(String cedula);

    //public Administrador buscarAdminLogin(String cedula, String password);

}
