package com.web.dpelos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dpelos.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    
}
