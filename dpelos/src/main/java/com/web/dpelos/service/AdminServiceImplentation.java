package com.web.dpelos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Administrador;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.AdminRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service

public class AdminServiceImplentation implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Administrador buscarAdministradorPorId(Long id){
        return adminRepository.findById(id).get();
    }
    
    @Override
    public List<Administrador> obtenerAdministradors(){
        return adminRepository.findAll();
    }

    @Override
    public void addAdministrador(Administrador admin){
        adminRepository.save(admin);
    }

    @Override
    public void deleteAdministrador(Long id){
        Administrador raza = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Administrador not found with id: " + id));

        if (raza != null) {
            adminRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void updateAdministrador(Administrador admin){
        adminRepository.save(admin);
    }

    @Override
    public Administrador buscarAdminLogin(String cedula, String password){
        return adminRepository.findByAdminCedulaAndPassword(cedula, password);
    }

}
