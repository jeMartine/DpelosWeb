package com.web.dpelos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.Administrador;

public interface AdminRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByAdminCedula(String adminCedula);
    //Administrador findByAdminCedulaAndPassword(String adminCedula, String password);
}
