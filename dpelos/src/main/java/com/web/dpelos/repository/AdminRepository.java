package com.web.dpelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.Administrador;

public interface AdminRepository extends JpaRepository<Administrador, Long> {
    Administrador findByAdminCedulaAndPassword(String adminCedula, String password);
}
