package com.web.dpelos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.dpelos.dto.AdministradorDTO;
import com.web.dpelos.entity.Administrador;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.AdminRepository;
import com.web.dpelos.repository.RoleRepository;
import com.web.dpelos.repository.UserRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service

public class AdminServiceImplentation implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AdministradorDTO toDTO(Administrador administrador) {
        if (administrador == null)
            return null;

        return new AdministradorDTO(
                administrador.getAdminId(),
                administrador.getAdminCedula());
    }

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

    public Administrador buscarAdminPorCedula(String cedula){
        return adminRepository.findByAdminCedula(cedula).orElseThrow(() -> new RuntimeException("Administrador no encontrado con la cédula: " + cedula));
    }

    /*@Override
    public Administrador buscarAdminLogin(String cedula){
        return adminRepository.findByAdminCedula(cedula);
    Optional<UserEntity> userEntityOpt = userRepository.findByUsername(cedula);
        
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            System.out.println("Admin encontrado: " + userEntity.getUsername());
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return adminRepository.findByAdminCedula(cedula);
            } else {
                System.out.println("Contraseña incorrecta para admin: " + cedula);
            }
        } else {
            System.out.println("Admin no encontrado para la cédula: " + cedula);
        }
        return null;*/
        //return adminRepository.findByAdminCedulaAndPassword(cedula, password);
    

}
