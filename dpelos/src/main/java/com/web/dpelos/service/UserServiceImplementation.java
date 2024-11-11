package com.web.dpelos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Role;
import com.web.dpelos.entity.UserEntity;
import com.web.dpelos.repository.RoleRepository;
import com.web.dpelos.repository.UserRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class UserServiceImplementation {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Transactional
    public void removeRoleFromUser(Long userId, Long roleId) {
        // Obtén el usuario de la base de datos
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Obtén el rol de la base de datos
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        // Elimina el rol de la lista de roles del usuario
        user.getRoles().remove(role);

        // Guarda los cambios en la base de datos
        userRepository.save(user);
    }
}
