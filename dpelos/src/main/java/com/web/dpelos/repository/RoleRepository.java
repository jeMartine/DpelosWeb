package com.web.dpelos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
    
}
