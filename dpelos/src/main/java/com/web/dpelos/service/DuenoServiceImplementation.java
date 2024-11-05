package com.web.dpelos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Role;
import com.web.dpelos.entity.UserEntity;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.exception.NotFoundException;
import com.web.dpelos.repository.DuenoRepository;
import com.web.dpelos.repository.MascotaRepository;
import com.web.dpelos.repository.RoleRepository;
import com.web.dpelos.repository.UserRepository;
import com.web.dpelos.repository.VeterinarioRepository;

import jakarta.transaction.Transactional;

@EnableAutoConfiguration
@Service
public class DuenoServiceImplementation implements DuenoService {
    @Autowired
    DuenoRepository duenoRepository;

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Dueno buscarDuenoPorId(Long id) {
        return duenoRepository.findById(id).get();
    }

    @Override
    public List<Dueno> obtenerDuenos() {
        return duenoRepository.findAll();
    }

    @Override
    public Dueno addDueno(Dueno dueno) {
        //assignUserToDueno(dueno);
        return duenoRepository.save(dueno);
    }

    @Transactional
    @Override
    public void deleteDueno(Long id) {
        Dueno dueno = duenoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dueno no encontrado con el id: " + id));

        if (dueno != null) {
            mascotaRepository.deleteAllByDueno(dueno);
            duenoRepository.deleteById(id);
        }
    }

    @Transactional
    @Override
    public Dueno updateDueno(Dueno dueno) {
        return duenoRepository.save(dueno);
    }

    @Override
    public Dueno buscarDuenoPorCedula(String cedulaDueno) {
        return duenoRepository.findByCedulaDueno(cedulaDueno);
    }

    /*public UserEntity assignUserToDueno(Dueno dueno) {
        if (dueno.getUser() == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(dueno.getCedulaDueno());
            userEntity.setPassword(passwordEncoder.encode("123"));
            Role roles = roleRepository.findByName("DUENO").get();
            userEntity.setRoles(List.of(roles));
            userRepository.save(userEntity);

            dueno.setUser(userEntity);
            return userEntity;
        }
        return dueno.getUser();
    }*/
}
